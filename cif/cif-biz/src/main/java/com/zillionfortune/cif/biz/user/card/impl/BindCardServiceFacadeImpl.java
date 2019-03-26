/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.card.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.biz.common.util.TransResult;
import com.zillionfortune.cif.biz.user.card.check.BindCardServiceParameterChecker;
import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.AuthGrade;
import com.zillionfortune.cif.common.enums.BindCardStatus;
import com.zillionfortune.cif.common.enums.PersonGradeType;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.BusinessFlowUtils;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.PersonBindCard;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.card.BindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.BindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardResponse;
import com.zillionfortune.cif.integration.payment.PaymentFrontService;
import com.zillionfortune.cif.integration.payment.dto.BaseResult;
import com.zillionfortune.cif.integration.payment.dto.BindCardQueryReq;
import com.zillionfortune.cif.integration.payment.dto.SmsCodeBindCardReq;
import com.zillionfortune.cif.integration.payment.dto.SmsCodeBindCardResult;
import com.zillionfortune.cif.service.user.PersonInfoService;
import com.zillionfortune.cif.service.user.PersonMemberService;
import com.zillionfortune.cif.service.user.card.PersonBindCardService;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: BindCardServiceFacadeImpl <br/>
 * Function:个人会员绑定银行卡服务接口实现类. <br/>
 * Date: 2016年12月12日 下午3:53:49 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class BindCardServiceFacadeImpl implements BindCardServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(BindCardServiceFacadeImpl.class);
	
	@Autowired
	private BindCardServiceParameterChecker checker;
	@Autowired
	private PersonMemberService personMemberService;
	@Autowired
	private PersonBindCardService personBindCardService;
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private PaymentFrontService paymentFrontService;

	/**
	 * @see com.zillionfortune.cif.facade.user.card.BindCardServiceFacade#bindCard(com.zillionfortune.cif.facade.user.card.dto.BindCardRequest)
	 */
	@Override
	public BindCardResponse bindCard(BindCardRequest req) {
		log.info("bindCard.req:" + JSONObject.toJSONString(req));
		BindCardResponse resp = null;
		try {
			// 参数校验
			checker.checkBindCardReq(req);
			
			PersonMember member = personMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (null == member) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 查询该卡是否已被绑定
			PersonBindCard bindCardSuccess = new PersonBindCard();
			bindCardSuccess.setCardNo(req.getBankcardNo());
			bindCardSuccess.setStatus(BindCardStatus.SUCCESS.code());
			bindCardSuccess = personBindCardService.queryPersonBindCard(bindCardSuccess);
			// 卡号已被绑定
			if (bindCardSuccess != null) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.BIND_CARD_EXIST.code(),
						ResultCode.BIND_CARD_EXIST.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 客户认证信息校验  只能绑定自己的银行卡  目前仅考虑身份证
			if (StringUtils.isNotEmpty(member.getCustomerId())) {
				PersonInfo personInfo = personInfoService.queryByCustomerId(member.getCustomerId());
				if (!personInfo.getPersonName().equals(req.getRealName()) || personInfo.getCertificateType() != req.getCertificateType()
						|| !personInfo.getCertificateNo().equals(req.getCertificateNo())) {
					resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_BIND_AUTH_FAIL.code(),
							ResultCode.CAN_NOT_BIND_AUTH_FAIL.desc());
					resp.setMemberId(req.getMemberId());
					return resp;
				}
			} else {
				// 查询身份信息是否已被绑定
				Map<String, Object> criteria = new HashMap<String, Object>();
				// criteria.put("personName", req.getRealName()); //  证件是个人唯一标识  
				criteria.put("certificateNo", req.getCertificateNo());
				criteria.put("certificateType", req.getCertificateType());
				List<PersonInfo> rsList = personInfoService.queryByCriteria(criteria);
				// 身份信息已被绑定
				if (rsList != null && rsList.size() > 0) {
					resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.AUTH_INFO_EXIST.code(),
							ResultCode.AUTH_INFO_EXIST.desc());
					resp.setMemberId(req.getMemberId());
					return resp;
				}
			}
			
			PersonBindCard bindCard = new PersonBindCard();
			bindCard.setMemberId(req.getMemberId());
			bindCard.setCardNo(req.getBankcardNo());
			bindCard = personBindCardService.queryPersonBindCard(bindCard);
			
			// 创建对象
			PersonBindCard newBindCard = new PersonBindCard();
			if (bindCard == null) {
				newBindCard.setMemberId(req.getMemberId());
				newBindCard.setCardNo(req.getBankcardNo());
			} else {
				newBindCard.setId(bindCard.getId());
			}
			newBindCard.setReserveMobile(req.getMobile());
			newBindCard.setStatus(BindCardStatus.BINDING.code());
			// 绑卡信息入库
			personBindCardService.saveOrUpdateBySelective(newBindCard);
			
			// 封装请求参数
			SmsCodeBindCardReq smsCodeBindCardRequest = new SmsCodeBindCardReq();
			BeanUtilsWrapper.copyProperties(smsCodeBindCardRequest, req);
			smsCodeBindCardRequest.setCardType(req.getBankCardType());
			
			// 短信签约发送
			SmsCodeBindCardResult httpResult = (SmsCodeBindCardResult) paymentFrontService.msgSignSend(smsCodeBindCardRequest);
			resp = new BindCardResponse();
			BeanUtilsWrapper.copyProperties(resp, httpResult);
			
			// 转换结果提示消息 
			TransResult.transPaymentResult(resp);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("bindCard.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * @see com.zillionfortune.cif.facade.user.card.BindCardServiceFacade#smsVerification(com.zillionfortune.cif.facade.user.card.dto.BindCardRequest)
	 */
	@Override
	public BindCardResponse smsVerification(BindCardRequest req) {
		log.info("smsVerification.req:" + JSONObject.toJSONString(req));
		BindCardResponse resp = null;
		try {
			// 参数校验
			checker.checkSmsVerificationReq(req);
			
			PersonMember member = personMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (null == member) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 封装请求参数
			SmsCodeBindCardReq smsCodeBindCardRequest = new SmsCodeBindCardReq();
			BeanUtilsWrapper.copyProperties(smsCodeBindCardRequest, req);
			smsCodeBindCardRequest.setCardType(req.getBankCardType());
			
			// 短信签约确认
			SmsCodeBindCardResult httpResult = (SmsCodeBindCardResult) paymentFrontService.msgSignConfirm(smsCodeBindCardRequest);
			resp = new BindCardResponse();
			BeanUtilsWrapper.copyProperties(resp, httpResult);
			
			PersonBindCard bindCard = new PersonBindCard();
			bindCard.setMemberId(req.getMemberId());
			bindCard.setCardNo(req.getBankcardNo());
			bindCard = personBindCardService.queryPersonBindCard(bindCard);
			// 短信签约成功
			if (resp.getRespCode().equals(RespCode.SUCCESS.code()) && resp.getResultCode().equals(ResultCode.SUCCESS.code())) {
				PersonBindCard upBindCard = new PersonBindCard();
				// 绑卡数据对象
				if (bindCard == null) {
					upBindCard.setMemberId(req.getMemberId());
					upBindCard.setReserveMobile(req.getMobile());
					upBindCard.setCardNo(req.getBankcardNo());
				} else {
					upBindCard.setId(bindCard.getId());
				}
				upBindCard.setBandId(httpResult.getSignId());
				upBindCard.setStatus(BindCardStatus.SUCCESS.code());
				upBindCard.setBindTime(new Date());
				
				PersonInfo personInfo = null;
				PersonMember upMember = null;
				// 未认证会员 则需要添加认证信息
				if (StringUtils.isEmpty(member.getCustomerId())) {
					// 添加客户信息对象
					personInfo = new PersonInfo();
					personInfo.setCustomerId(BusinessFlowUtils.generate(CommonConstants.PERSONAL_INFO_PREFIX));
					personInfo.setPersonName(req.getRealName());
					personInfo.setCertificateType(req.getCertificateType());
					personInfo.setCertificateNo(req.getCertificateNo());
					
					// 更改会员认证等级  ---- grade 第一位约定为认证等级
					upMember = new PersonMember();
					StringBuffer gradeSb = new StringBuffer(member.getGrade());
					gradeSb = gradeSb.replace(PersonGradeType.AUTH.getCode()-1, PersonGradeType.AUTH.getCode(), AuthGrade.AUTHENTICATED.getCode());
					upMember.setGrade(gradeSb.toString());
					upMember.setMemberId(req.getMemberId());
					upMember.setCustomerId(personInfo.getCustomerId());
					upMember.setRealNameTime(new Date());
					upMember.setModifyTime(new Date());
				}
				// 绑卡、认证、落地客户信息
				personBindCardService.authBindCardByMember(upMember, upBindCard, personInfo);
				return resp;
			}
			// 业务处理失败需更改绑定状态---> 绑定失败       （另：验证码输错则无需更改状态）TODO：支付网关暂未接入短信平台
			// 签约失败并且前置状态是绑定中， 则将绑定状态置为 绑定失败
			if (bindCard != null && bindCard.getStatus() == BindCardStatus.BINDING.code()) {
				PersonBindCard upBindCard = new PersonBindCard();
				upBindCard.setId(bindCard.getId());
				upBindCard.setStatus(BindCardStatus.FAIL.code());
				personBindCardService.saveOrUpdateBySelective(upBindCard);
			}
			// 转换结果提示消息 
			TransResult.transPaymentResult(resp);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("smsVerification.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * @see com.zillionfortune.cif.facade.user.card.BindCardServiceFacade#unBindCard(com.zillionfortune.cif.facade.user.card.dto.BindCardRequest)
	 */
	@Override
	public BindCardResponse unBindCard(BindCardRequest req) {
		log.info("unBindCard.req:" + JSONObject.toJSONString(req));
		BindCardResponse resp = null;
		try {
			// 参数校验
			checker.checkUnBindCardReq(req);
			
			PersonMember member = personMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (null == member) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			PersonBindCard bindCard = new PersonBindCard();
			bindCard.setMemberId(req.getMemberId());
			bindCard.setCardNo(req.getBankcardNo());
			bindCard.setStatus(BindCardStatus.SUCCESS.code());
			bindCard = personBindCardService.queryPersonBindCard(bindCard);
			// 银行卡未绑定
			if (bindCard == null) {
				resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.BIND_CARD_NOT_EXIST.code(),
						ResultCode.BIND_CARD_NOT_EXIST.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 封装请求参数
			SmsCodeBindCardReq httpRequest = new SmsCodeBindCardReq();
			httpRequest.setMemberId(req.getMemberId());
			httpRequest.setBankcardNo(req.getBankcardNo());
			httpRequest.setBankCode(req.getBankCode());
			
			// 解绑银行卡
			BaseResult httpResult = paymentFrontService.unBindCard(httpRequest);
			resp = new BindCardResponse();
			BeanUtilsWrapper.copyProperties(resp, httpResult);
			
			// 解绑成功
			if (resp.getRespCode().equals(RespCode.SUCCESS.code()) && resp.getResultCode().equals(ResultCode.SUCCESS.code())) {
				PersonBindCard upBindCard = new PersonBindCard();
				upBindCard.setId(bindCard.getId());
				// 更新绑定状态值 ---- 已解绑
				upBindCard.setStatus(BindCardStatus.UNBIND.code());
				upBindCard.setUnbindTime(new Date());
				personBindCardService.saveOrUpdateBySelective(upBindCard);
			}else {
				// 转换结果提示消息 
				TransResult.transPaymentResult(resp);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BindCardResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("unBindCard.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * @see com.zillionfortune.cif.facade.user.card.BindCardServiceFacade#queryBindCard(com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest)
	 */
	@Override
	public BindCardQueryResponse queryBindCard(BindCardQueryRequest req) {
		log.info("queryBindCard.req:" + JSONObject.toJSONString(req));
		BindCardQueryResponse resp = null;
		try {
			// 参数校验
			checker.checkQueryBindCardReq(req);
			
			PersonMember member = personMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (null == member) {
				resp = new BindCardQueryResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 封装请求参数
			BindCardQueryReq httpRequest = new BindCardQueryReq();
			httpRequest.setMemberId(req.getMemberId());
			httpRequest.setIdCard(req.getCertificateNo());
			httpRequest.setCardIdxNo(req.getBankcardNo());
			
			// 查询已绑定银行卡
			BaseResult httpResult = paymentFrontService.queryBindCard(httpRequest);
			resp = new BindCardQueryResponse();
			BeanUtilsWrapper.copyProperties(resp, httpResult);
			
			// 转换结果提示消息 
			TransResult.transPaymentResult(resp);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BindCardQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BindCardQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("queryBindCard.resp:" + JSONObject.toJSONString(resp));
		}
		
		return resp;
	}

}
