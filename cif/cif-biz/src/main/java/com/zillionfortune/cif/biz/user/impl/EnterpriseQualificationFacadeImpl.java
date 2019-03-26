/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseQualification;
import com.zillionfortune.cif.facade.user.EnterpriseQualificationFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateResponse;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseQualificationService;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: EnterpriseQualificationFacadeImpl <br/>
 * Function: 企业资质信息服务 接口实现 <br/>
 * Date: 2016年12月20日 下午1:35:58 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseQualificationFacadeImpl implements EnterpriseQualificationFacade {
	private static Logger log = LoggerFactory.getLogger(EnterpriseQualificationFacadeImpl.class);
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	@Autowired
	private EnterpriseQualificationService enterpriseQualificationService;
	
	
	/**
	 * @see com.zillionfortune.cif.facade.user.EnterpriseQualificationFacade#qualificationUpdate(com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateRequest)
	 */
	@Override
	public EnterpriseQualificationUpdateResponse qualificationUpdate(EnterpriseQualificationUpdateRequest req) {
		log.info("qualificationUpdate.req:" + JSONObject.toJSONString(req));
		EnterpriseQualificationUpdateResponse resp = null;
		try {
			// 参数校验
			checkQualificationUpdateParam(req);
			
			EnterpriseMember member  = enterpriseMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (member == null) {
				resp = new EnterpriseQualificationUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new EnterpriseQualificationUpdateResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 更新
			EnterpriseQualification qualification = new EnterpriseQualification();
			BeanUtilsWrapper.copyProperties(qualification, req);
			qualification.setCustomerId(member.getCustomerId());
			
			enterpriseQualificationService.updateByCustomerIdKeySelective(qualification);
			resp = new EnterpriseQualificationUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseQualificationUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseQualificationUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("qualificationUpdate.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}
	
	/**
	 * checkQualificationUpdateParam:企业资质信息更新_参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	private void checkQualificationUpdateParam(EnterpriseQualificationUpdateRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId())) {
			throw new BusinessException("memberId 不能为空");
		}
		if (StringUtils.isEmpty(req.getAccountOpeningLicenseUrl()) &&
				StringUtils.isEmpty(req.getBusinessLicenceUrl()) && StringUtils.isEmpty(req.getLegalPersonCertificateBackUrl()) && 
				StringUtils.isEmpty(req.getLegalPersonCertificateFrontUrl()) && StringUtils.isEmpty(req.getOrganizationCodeCertificateUrl()) &&
				StringUtils.isEmpty(req.getPowerOfAttorneyUrl()) && StringUtils.isEmpty(req.getServiceApplicationUrl()) &&
				StringUtils.isEmpty(req.getTaxRegistrationCertificateLocalUrl()) && StringUtils.isEmpty(req.getTaxRegistrationCertificateUrl())) {
			throw new BusinessException("所有要更新的请求字段不能同时为空");
		}
	}

}
