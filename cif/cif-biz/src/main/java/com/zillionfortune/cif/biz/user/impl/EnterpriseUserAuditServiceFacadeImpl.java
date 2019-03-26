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
import com.zillionfortune.cif.common.enums.EnterpriseAuditStatusEnum;
import com.zillionfortune.cif.common.enums.EnterpriseGradeType;
import com.zillionfortune.cif.common.enums.LegalPersonAuditStatusEnum;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.facade.user.EnterpriseUserAuditServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditResponse;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;

/**
 * ClassName: EnterpriseUserAuditServiceFacadeImpl <br/>
 * Function:  企业会员认证信息审核接口实现. <br/>
 * Date: 2016年12月27日 下午5:55:14 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserAuditServiceFacadeImpl implements EnterpriseUserAuditServiceFacade {
	private static Logger log = LoggerFactory.getLogger(EnterpriseUserAuditServiceFacadeImpl.class);
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	/**
	 * @see com.zillionfortune.cif.facade.user.EnterpriseUserAuditServiceFacade#audit(com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditRequest)
	 */
	@Override
	public EnterpriseUserAuditResponse audit(EnterpriseUserAuditRequest req) {
		log.info("audit.req:" + JSONObject.toJSONString(req));
		EnterpriseUserAuditResponse resp = null;
		try {
			// 参数校验
			checkAuditParameter(req);
			
			EnterpriseMember enterpriseMember = enterpriseMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (enterpriseMember == null ) {
				resp = new EnterpriseUserAuditResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (enterpriseMember.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new EnterpriseUserAuditResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			// 截取认证等级值
			String authGrade = enterpriseMember.getGrade().substring(EnterpriseGradeType.AUTH.getCode()-1, EnterpriseGradeType.AUTH.getCode());
			// 更新审核状态值、认证等级
			EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
			enterpriseInfo.setEnterpriseAuditStatus(req.getEnterpriseAuditStatus());
			enterpriseInfo.setLegalPersonAuditStatus(req.getLegalPersonAuditStatus());
			enterpriseInfo.setCustomerId(enterpriseMember.getCustomerId());
			enterpriseInfo.setEnterpriseAuditComment(req.getEnterpriseAuditComment());
			enterpriseInfo.setLegalPersonAuditComment(req.getLegalPersonAuditComment());

			authGrade = enterpriseInfoService.auditAuth(enterpriseInfo, authGrade);
			
			resp = new EnterpriseUserAuditResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
			resp.setAuthGrade(authGrade);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserAuditResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserAuditResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("audit.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}
	
	/**
	 * checkAuditParameter:检验入参. <br/>
	 *
	 * @param req
	 * @throws BusinessException 
	 */
	private void checkAuditParameter(EnterpriseUserAuditRequest req) throws BusinessException {
		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId())) {
			throw new BusinessException("memberId不能为空");
		}
		if (null == req.getEnterpriseAuditStatus() && null == req.getLegalPersonAuditStatus()) {
			throw new BusinessException("enterpriseAuditStatus 和  legalPersonAuditStatus不能同时为空");
		}
		if (null != req.getEnterpriseAuditStatus() && 
				null == EnterpriseAuditStatusEnum.getEnumItem(req.getEnterpriseAuditStatus())) {
			throw new BusinessException("enterpriseAuditStatus 参数值不在约定范围内");
		}
		if (null != req.getLegalPersonAuditStatus() && 
				null == LegalPersonAuditStatusEnum.getEnumItem(req.getLegalPersonAuditStatus())) {
			throw new BusinessException("legalPersonAuditStatus 参数值不在约定范围内");
		}
	}

}
