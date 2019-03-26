/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.biz.user.check.EnterprisePasswordServiceParameterChecker;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordVerifyResponse;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;

/**
 * ClassName: EnterprisePasswordServiceFacadeImpl <br/>
 * Function: 企业会员 重置/更新登录密码、重置/登录/验证交易密码. <br/>
 * Date: 2016年12月7日 上午10:04:10 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterprisePasswordServiceFacadeImpl implements EnterprisePasswordServiceFacade {

	private static Logger log = LoggerFactory.getLogger(EnterprisePasswordServiceFacadeImpl.class);

	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;

	@Autowired
	private EnterprisePasswordServiceParameterChecker parameterChecker;

	/**
	 * 企业会员重置登录密码.
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#retrieveLoginPassword(com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest)
	 */
	@Override
	public EnterpriseLoginPasswordRetrieveResponse retrieveLoginPassword(EnterpriseLoginPasswordRetrieveRequest req) {
		log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.req:" + JSON.toJSONString(req));
		// 响应结果定义
		EnterpriseLoginPasswordRetrieveResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkEnterpriseLoginPasswordRetrieveRequest(req);
			
			// 获取会员信息
			EnterpriseMember currentEnterpriseMember = new EnterpriseMember();
			currentEnterpriseMember.setCustomerNo(req.getCustomerNo());
			currentEnterpriseMember = enterpriseMemberService.queryEnterpriseMember(currentEnterpriseMember);
			if (currentEnterpriseMember == null) {
				// 企业号不存在的场合，返回企业号不存在
				resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(),
						ResultCode.CUSTOMER_NO_NOT_FOUND.code(), ResultCode.CUSTOMER_NO_NOT_FOUND.desc());

				log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证操作员是否存在
			EnterpriseOperator currentEnterpriseOperator = new EnterpriseOperator();
			currentEnterpriseOperator = enterpriseOperatorService.queryEnterpriseOperator(req.getCustomerNo(), req.getUserName());
			if (currentEnterpriseOperator == null) {
				// 操作员不存在的场合，返回操作员不存在
				resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(),
						ResultCode.OPERATOR_NOT_FOUND.code(), ResultCode.OPERATOR_NOT_FOUND.desc());

				resp.setMemberId(currentEnterpriseMember.getMemberId());

				log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证操作员状态是否为正常
			if (currentEnterpriseOperator.getStatus() == null
					|| currentEnterpriseOperator.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				// 操作员状态为【正常】以外的场合，返回会员已冻结或已注销不能重置密码
				resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(),
						ResultCode.CAN_NOT_RETRIEVE_PASSWORD_OPERATOR.code(), ResultCode.CAN_NOT_RETRIEVE_PASSWORD_OPERATOR.desc());

				resp.setMemberId(currentEnterpriseOperator.getMemberId());

				log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 重置登录密码
			EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
			enterpriseOperator.setId(currentEnterpriseOperator.getId());
			enterpriseOperator.setPassword(req.getNewPassword().toUpperCase());
			enterpriseOperator.setModifyTime(new Date());
			enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);

			resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(currentEnterpriseOperator.getMemberId());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseLoginPasswordRetrieveResponse(RespCode.FAIL.code());
			}
		}
		
		log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

		return resp;

	}

	/**
	 * 企业会员更新登录密码.
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#modifyLoginPassword(com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest)
	 */
	@Override
	public EnterpriseLoginPasswordModifyResponse modifyLoginPassword(EnterpriseLoginPasswordModifyRequest req) {
		log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.req:" + JSON.toJSONString(req));
		// 响应结果定义
		EnterpriseLoginPasswordModifyResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkEnterpriseLoginPasswordModifyRequest(req);
			Long operatorId = Long.valueOf(req.getOperatorId());

			// 验证操作员是否存在
			EnterpriseOperator currentEnterpriseOperator = enterpriseOperatorService.queryByOperatorId(operatorId);
			if (currentEnterpriseOperator == null) {
				// 操作员不存在的场合，返回操作员不存在
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.OPERATOR_NOT_FOUND.code(), ResultCode.OPERATOR_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 校验请求参数memberId是否有效
			if (!req.getMemberId().equals(currentEnterpriseOperator.getMemberId())) {
				// 【请求参数operatorId对应的memberId】和【请求参数memberId】不一致的场合
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.INVALID_MEMBERID.code(), ResultCode.INVALID_MEMBERID.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 获取会员信息
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(currentEnterpriseOperator.getMemberId());
			if (currentEnterpriseMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.USER_NOT_FOUND.code(), ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证操作员状态是否为正常
			if (currentEnterpriseOperator.getStatus() == null
					|| currentEnterpriseOperator.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				// 操作员状态为【正常】以外的场合，返回会员已冻结或已注销不能更新密码
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.CAN_NOT_MODIFY_PASSWORD_OPERATOR.code(), ResultCode.CAN_NOT_MODIFY_PASSWORD_OPERATOR.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证原密码是否正确
			if (!currentEnterpriseOperator.getPassword().equals(req.getOrgiPassword().toUpperCase())) {
				// 请求参数orgiPassword和原密码不一致的场合，返回原密码不正确
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.ORGIPASSWORD_ERROR.code(), ResultCode.ORGIPASSWORD_ERROR.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证新密码和原密码是否重复
			if (currentEnterpriseOperator.getPassword().equals(req.getNewPassword().toUpperCase())) {
				// 新密码和原密码一致的场合，返回新密码和原密码不能重复
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.code(), ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			
			// 更新登录密码
			EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
			enterpriseOperator.setId(operatorId);
			enterpriseOperator.setPassword(req.getNewPassword().toUpperCase());
			enterpriseOperator.setModifyTime(new Date());
			enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);

			resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(req.getMemberId());
			resp.setOperatorId(operatorId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseLoginPasswordModifyResponse(RespCode.FAIL.code());
			}
		}

		log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}
	
	/**
	 * 企业会员设置交易密码.
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#setTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public EnterpriseTradePasswordSetResponse setTradePassword(String memberId, String password) {
	
		log.info("EnterprisePasswordServiceFacadeImpl.setTradePassword.req:{memberId:" + memberId + "}" );
		
		EnterpriseTradePasswordSetResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(password)){
				throw new BusinessException("password字段不能为空");
			}

			//======2.验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(memberId);
			if (currentEnterpriseMember == null) {
				
				resp = new EnterpriseTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("EnterprisePasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常
			if (currentEnterpriseMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentEnterpriseMember.getStatus() ) {
				
				resp = new EnterpriseTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_SET_PASSWORD.code(), 
						ResultCode.CAN_NOT_SET_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.校验是否重复设置交易密码
			if(StringUtils.isNotBlank(currentEnterpriseMember.getTradePwd())){
				resp = new EnterpriseTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_REPEAT_SET_PASSWORD.code(), 
						ResultCode.CAN_NOT_REPEAT_SET_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======5.设置交易密码
			EnterpriseMember enterpriseMember = new EnterpriseMember();
			enterpriseMember.setMemberId(memberId);
			enterpriseMember.setTradePwd(StringUtils.upperCase(password));
			enterpriseMember.setModifyTime(new Date());
			enterpriseMemberService.updateByMemberIdSelective(enterpriseMember);

			resp = new EnterpriseTradePasswordSetResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseTradePasswordSetResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseTradePasswordSetResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("EnterprisePasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}
	
	/**
	 * 企业会员重置交易密码.
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#retrieveTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public EnterpriseTradePasswordRetrieveResponse retrieveTradePassword(String memberId, String newPassword) {
	
		log.info("EnterprisePasswordServiceFacadeImpl.retrieveTradePassword.req:{memberId:" + memberId + "}" );
		
		EnterpriseTradePasswordRetrieveResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(newPassword)){
				throw new BusinessException("newPassword字段不能为空");
			}

			//======2.验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(memberId);
			if (currentEnterpriseMember == null) {
				
				resp = new EnterpriseTradePasswordRetrieveResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("EnterprisePasswordServiceFacadeImpl.retrieveTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常
			if (currentEnterpriseMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentEnterpriseMember.getStatus() ) {
				
				resp = new EnterpriseTradePasswordRetrieveResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_RETRIEVE_PASSWORD.code(), 
						ResultCode.CAN_NOT_RETRIEVE_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.重置交易密码
			EnterpriseMember enterpriseMember = new EnterpriseMember();
			enterpriseMember.setMemberId(memberId);
			enterpriseMember.setTradePwd(StringUtils.upperCase(newPassword));
			enterpriseMember.setModifyTime(new Date());
			enterpriseMemberService.updateByMemberIdSelective(enterpriseMember);

			resp = new EnterpriseTradePasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseTradePasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseTradePasswordRetrieveResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("EnterprisePasswordServiceFacadeImpl.retrieveTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	/**
	 * 企业会员更新交易密码.
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#modifyTradePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public EnterpriseTradePasswordModifyResponse modifyTradePassword(String memberId, String newPassword, String orgiPassword) {
		
		log.info("EnterprisePasswordServiceFacadeImpl.modifyTradePassword.req:{memberId:" + memberId + "}" );
	
		EnterpriseTradePasswordModifyResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(newPassword)){
				throw new BusinessException("newPassword字段不能为空");
			}
			
			if(StringUtils.isBlank(orgiPassword)){
				throw new BusinessException("orgiPassword字段不能为空");
			}


			//======2.验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(memberId);
			if (currentEnterpriseMember == null) {
				
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("EnterprisePasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常 
			if (currentEnterpriseMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentEnterpriseMember.getStatus() ) {
				
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_MODIFY_PASSWORD.code(), 
						ResultCode.CAN_NOT_MODIFY_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.验证原密码是否正确
			if (!StringUtils.upperCase(orgiPassword).equals(currentEnterpriseMember.getTradePwd())) {
				
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.ORGIPASSWORD_ERROR.code(), 
						ResultCode.ORGIPASSWORD_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======5.证新密码和原密码是否重复
			if (StringUtils.upperCase(newPassword).equals(currentEnterpriseMember.getTradePwd())) {
				
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.code(),
						ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			//======6.更新交易密码
			EnterpriseMember enterpriseMember = new EnterpriseMember();
			enterpriseMember.setMemberId(memberId);
			enterpriseMember.setTradePwd(StringUtils.upperCase(newPassword));
			enterpriseMember.setModifyTime(new Date());
			enterpriseMemberService.updateByMemberIdSelective(enterpriseMember);

			resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseTradePasswordModifyResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
			}

		}
		
		log.info("EnterprisePasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	
	/**
	 * 企业会员验证交易密码
	 * @see com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade#verifyTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public EnterpriseTradePasswordVerifyResponse verifyTradePassword(String memberId, String password) {
		
		log.info("EnterprisePasswordServiceFacadeImpl.verifyTradePassword.req:{memberId:" + memberId + "}" );
		
		EnterpriseTradePasswordVerifyResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(password)){
				throw new BusinessException("password字段不能为空");
			}

			//======2.验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(memberId);
			if (currentEnterpriseMember == null) {
				
				resp = new EnterpriseTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("EnterprisePasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常 
			if (currentEnterpriseMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentEnterpriseMember.getStatus() ) {
				
				resp = new EnterpriseTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_VERIFY_PASSWORD.code(), 
						ResultCode.CAN_NOT_VERIFY_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.验证交易密码是否正确
			if (!StringUtils.upperCase(password).equals(currentEnterpriseMember.getTradePwd()) ) {
				
				resp = new EnterpriseTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.TRADE_PASSWORD_ERROR.code(), 
						ResultCode.TRADE_PASSWORD_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("EnterprisePasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			resp = new EnterpriseTradePasswordVerifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseTradePasswordVerifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new EnterpriseTradePasswordVerifyResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("EnterprisePasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	
	}

}

