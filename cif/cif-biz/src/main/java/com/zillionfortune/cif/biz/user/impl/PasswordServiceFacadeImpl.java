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
import com.zillionfortune.cif.biz.user.check.IndividualPasswordServiceParameterChecker;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.PasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordVerifyResponse;
import com.zillionfortune.cif.service.user.PersonMemberService;

/**
 * ClassName: PasswordServiceFacadeImpl <br/>
 * Function: 个人会员 重置登录密码/登录密码更新 接口实现. <br/>
 * Date: 2016年12月9日 下午2:05:21 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class PasswordServiceFacadeImpl implements PasswordServiceFacade {

	private static Logger log = LoggerFactory.getLogger(PasswordServiceFacadeImpl.class);

	@Autowired
	private PersonMemberService personMemberService;

	@Autowired
	private IndividualPasswordServiceParameterChecker parameterChecker;

	/**
	 * 个人会员重置登录密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#retrieveLoginPassword(com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest)
	 */
	@Override
	public IndividualLoginPasswordRetrieveResponse retrieveLoginPassword(IndividualLoginPasswordRetrieveRequest req) {
		log.info("PasswordServiceFacadeImpl.retrieveLoginPassword.req:" + JSON.toJSONString(req));
		// 响应结果定义
		IndividualLoginPasswordRetrieveResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkIndividualLoginPasswordRetrieveRequest(req);

			// 验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(req.getMemberId());
			if (currentPersonMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new IndividualLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(),
						ResultCode.USER_NOT_FOUND.code(), ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证会员状态是否为正常
			if (currentPersonMember.getStatus() == null
					|| currentPersonMember.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				// 会员状态为【正常】以外的场合，返回会员已冻结或已注销不能重置密码
				resp = new IndividualLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(),
						ResultCode.CAN_NOT_RETRIEVE_PASSWORD.code(), ResultCode.CAN_NOT_RETRIEVE_PASSWORD.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 重置登录密码
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(req.getMemberId());
			personMember.setPassword(req.getNewPassword().toUpperCase());
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new IndividualLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(req.getMemberId());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualLoginPasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualLoginPasswordRetrieveResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("PasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	/**
	 * 个人会员更新登录密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#modifyLoginPassword(com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest)
	 */
	@Override
	public IndividualLoginPasswordModifyResponse modifyLoginPassword(IndividualLoginPasswordModifyRequest req) {
		log.info("PasswordServiceFacadeImpl.modifyLoginPassword.req:" + JSON.toJSONString(req));
		// 响应结果定义
		IndividualLoginPasswordModifyResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkIndividualLoginPasswordModifyRequest(req);

			// 验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(req.getMemberId());
			if (currentPersonMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.USER_NOT_FOUND.code(), ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			// 验证会员状态是否为正常
			if (currentPersonMember.getStatus() == null
					|| currentPersonMember.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				// 会员状态为【正常】以外的场合，返回会员已冻结或已注销不能更新密码
				resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.CAN_NOT_MODIFY_PASSWORD.code(), ResultCode.CAN_NOT_MODIFY_PASSWORD.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证原密码是否正确
			if (!currentPersonMember.getPassword().equals(req.getOrgiPassword().toUpperCase())) {
				// 请求参数orgiPassword和原密码不一致的场合，返回原密码不正确
				resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.ORGIPASSWORD_ERROR.code(), ResultCode.ORGIPASSWORD_ERROR.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 验证新密码和原密码是否重复
			if (currentPersonMember.getPassword().equals(req.getNewPassword().toUpperCase())) {
				// 新密码和原密码一致的场合，返回新密码和原密码不能重复
				resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(),
						ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.code(), ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.desc());

				resp.setMemberId(req.getMemberId());

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			
			// 更新登录密码
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(req.getMemberId());
			personMember.setPassword(req.getNewPassword().toUpperCase());
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(req.getMemberId());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualLoginPasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualLoginPasswordModifyResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}

		log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	/**
	 * 个人会员设置交易密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#setTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public IndividualTradePasswordSetResponse setTradePassword(String memberId,String password) {

		log.info("PasswordServiceFacadeImpl.setTradePassword.req:{memberId:" + memberId + "}" );
		
		IndividualTradePasswordSetResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(password)){
				throw new BusinessException("password字段不能为空");
			}

			//======2.验证会员是否存在
			PersonMember currentPersonMember= personMemberService.queryByMemberId(memberId);
			if (currentPersonMember == null) {
				
				resp = new IndividualTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("PasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常
			if (currentPersonMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentPersonMember.getStatus() ) {
				
				resp = new IndividualTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_SET_PASSWORD.code(), 
						ResultCode.CAN_NOT_SET_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.校验是否重复设置交易密码
			if(StringUtils.isNotBlank(currentPersonMember.getTradePwd())){
				resp = new IndividualTradePasswordSetResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_REPEAT_SET_PASSWORD.code(), 
						ResultCode.CAN_NOT_REPEAT_SET_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======5.设置交易密码
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(memberId);
			personMember.setTradePwd(StringUtils.upperCase(password));
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);
			
			resp = new IndividualTradePasswordSetResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualTradePasswordSetResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualTradePasswordSetResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("PasswordServiceFacadeImpl.setTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	
	}

	/**
	 * 个人会员重置交易密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#retrieveTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public IndividualTradePasswordRetrieveResponse retrieveTradePassword(String memberId, String newPassword) {
		
		log.info("PasswordServiceFacadeImpl.retrieveTradePassword.req:{memberId:" + memberId + "}" );
		
		IndividualTradePasswordRetrieveResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(newPassword)){
				throw new BusinessException("newPassword字段不能为空");
			}

			//======2.验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(memberId);
			if (currentPersonMember == null) {
				
				resp = new IndividualTradePasswordRetrieveResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("PasswordServiceFacadeImpl.retrieveTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常
			if (currentPersonMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentPersonMember.getStatus() ) {
				
				resp = new IndividualTradePasswordRetrieveResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_RETRIEVE_PASSWORD.code(), 
						ResultCode.CAN_NOT_RETRIEVE_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.retrieveLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.重置交易密码
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(memberId);
			personMember.setTradePwd(StringUtils.upperCase(newPassword));
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new IndividualTradePasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualTradePasswordRetrieveResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualTradePasswordRetrieveResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("PasswordServiceFacadeImpl.retrieveTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	
	}

	/**
	 * 个人会员更新交易密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#modifyTradePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IndividualTradePasswordModifyResponse modifyTradePassword(String memberId, String newPassword, String orgiPassword) {

		log.info("PasswordServiceFacadeImpl.modifyTradePassword.req:{memberId:" + memberId + "}" );
	
		IndividualTradePasswordModifyResponse resp;

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
			PersonMember currentPersonMember = personMemberService.queryByMemberId(memberId);
			if (currentPersonMember == null) {
				
				resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("PasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常 
			if (currentPersonMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentPersonMember.getStatus() ) {
				
				resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_MODIFY_PASSWORD.code(), 
						ResultCode.CAN_NOT_MODIFY_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.验证原密码是否正确
			if (!StringUtils.upperCase(orgiPassword).equals(currentPersonMember.getTradePwd())) {
				
				resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.ORGIPASSWORD_ERROR.code(), 
						ResultCode.ORGIPASSWORD_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======5.证新密码和原密码是否重复
			if (StringUtils.upperCase(newPassword).equals(currentPersonMember.getTradePwd())) {
				
				resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(),ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.code(),
						ResultCode.NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.modifyLoginPassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			//======6.更新交易密码
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(memberId);
			personMember.setTradePwd(StringUtils.upperCase(newPassword));
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualTradePasswordModifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualTradePasswordModifyResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
			}

		}
		
		log.info("PasswordServiceFacadeImpl.modifyTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	
	}

	/**
	 * 个人会员验证交易密码.
	 * @see com.zillionfortune.cif.facade.user.PasswordServiceFacade#verifyTradePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public IndividualTradePasswordVerifyResponse verifyTradePassword(String memberId, String password) {

		
		log.info("PasswordServiceFacadeImpl.verifyTradePassword.req:{memberId:" + memberId + "}" );
		
		IndividualTradePasswordVerifyResponse resp;

		try {
			
			//======1.请求参数校验
			if(StringUtils.isBlank(memberId)){
				throw new BusinessException("memberId字段不能为空");
			}
			
			if(StringUtils.isBlank(password)){
				throw new BusinessException("password字段不能为空");
			}

			//======2.验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(memberId);
			if (currentPersonMember == null) {
				
				resp = new IndividualTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(memberId);
				
				log.info("PasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
		
			//======3.验证会员状态是否为正常 
			if (currentPersonMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != currentPersonMember.getStatus() ) {
				
				resp = new IndividualTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_VERIFY_PASSWORD.code(), 
						ResultCode.CAN_NOT_VERIFY_PASSWORD.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.验证交易密码是否正确
			if (!StringUtils.upperCase(password).equals(currentPersonMember.getTradePwd()) ) {
				
				resp = new IndividualTradePasswordVerifyResponse(RespCode.SUCCESS.code(),ResultCode.TRADE_PASSWORD_ERROR.code(), 
						ResultCode.TRADE_PASSWORD_ERROR.desc());
				resp.setMemberId(memberId);

				log.info("PasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			resp = new IndividualTradePasswordVerifyResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new IndividualTradePasswordVerifyResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(),
						e.getMessage());
			} else {
				resp = new IndividualTradePasswordVerifyResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
	
		log.info("PasswordServiceFacadeImpl.verifyTradePassword.resp:" + JSON.toJSONString(resp));

		return resp;
	
	}

}
