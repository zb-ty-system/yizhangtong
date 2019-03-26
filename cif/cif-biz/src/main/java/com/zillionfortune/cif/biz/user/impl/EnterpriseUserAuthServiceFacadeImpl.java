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
import com.zillionfortune.cif.biz.user.check.EnterpriseUserAuthParameterChecker;
import com.zillionfortune.cif.common.enums.AuthGrade;
import com.zillionfortune.cif.common.enums.EnterpriseAuthGrade;
import com.zillionfortune.cif.common.enums.EnterpriseGradeType;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.facade.user.EnterpriseUserAuthServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceResponse;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;

/**
 * ClassName: EnterpriseUserAuthServiceFacadeImpl <br/>
 * Function: 企业会员认证服务实现. <br/>
 * Date: 2016年12月7日 上午9:53:30 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserAuthServiceFacadeImpl implements EnterpriseUserAuthServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(EnterpriseUserAuthServiceFacadeImpl.class);
	@Autowired
	private EnterpriseUserAuthParameterChecker parameterChecker;
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;

	/**
	 * @see com.zillionfortune.cif.facade.user.EnterpriseUserAuthServiceFacade#realNameAuth(com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest)
	 */
	@Override
	public EnterpriseUserAuthServiceResponse realNameAuth(EnterpriseUserAuthServiceRequest req) {
		log.info("realNameAuth.req:" + JSONObject.toJSONString(req));
		EnterpriseUserAuthServiceResponse resp = null;
		try {
			// 参数校验
			parameterChecker.checkRealNameAuthReq(req);
			
			EnterpriseInfo info =  enterpriseInfoService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if ( info == null ) {
				resp = new EnterpriseUserAuthServiceResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			EnterpriseMember member = enterpriseMemberService.queryByMemberId(req.getMemberId());
			// 认证等级取Grade字段第一位
			String authGrade = member.getGrade().substring(EnterpriseGradeType.AUTH.getCode()-1,EnterpriseGradeType.AUTH.getCode()); 
			// 判断会员认证等级  若不为未认证则返回提示
			if (!AuthGrade.UNAUTHORIZED.getCode().equals(authGrade)) {
				resp = new EnterpriseUserAuthServiceResponse(RespCode.SUCCESS.code(), ResultCode.MEMBER_IS_AUTHENTICATED.code(),
						ResultCode.MEMBER_IS_AUTHENTICATED.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 校验身份信息 不通过则返回提示 
			if (!req.getCertificateType().equals(String.valueOf(info.getCertificateType())) ||
					!req.getCertificateNo().equals(info.getCertificateNo()) || !req.getEnterpriseName().equals(info.getEnterpriseName())) {
				resp = new EnterpriseUserAuthServiceResponse(RespCode.SUCCESS.code(), ResultCode.REAL_NAME_AUTH_FAIL.code(),
						ResultCode.REAL_NAME_AUTH_FAIL.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 校验通过、则更新认证等级
			enterpriseMemberService.updateGrade(req.getMemberId(),member.getGrade(), EnterpriseAuthGrade.AUTH_SUCCESS.getCode(),
					EnterpriseGradeType.AUTH.getCode());
			
			resp = new EnterpriseUserAuthServiceResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserAuthServiceResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserAuthServiceResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("realNameAuth.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

}
