/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.biz.user.check.UserInfoQueryServiceParameterChecker;
import com.zillionfortune.cif.common.enums.AuthGrade;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.UserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualBasicInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.UserAuthInfoQueryResponse;
import com.zillionfortune.cif.service.user.PersonInfoService;
import com.zillionfortune.cif.service.user.PersonMemberService;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: UserInfoQueryServiceFacadeImpl <br/>
 * Function: 个人会员信息查询服务实现. <br/>
 * Date: 2016年11月10日 下午6:06:48 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class UserInfoQueryServiceFacadeImpl implements
		UserInfoQueryServiceFacade {
	private static Logger log = LoggerFactory
			.getLogger(UserInfoQueryServiceFacadeImpl.class);

	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private PersonMemberService personMemberService;
	@Autowired
	private UserInfoQueryServiceParameterChecker userInfoQueryServiceParamentChecker;

	@Override
	public IndividualBasicInfoQueryResponse queryUserInfo(
			IndividualInfoQueryRequest request) {
		log.info("queryUserInfo.req:" + JSONObject.toJSONString(request));
		IndividualBasicInfoQueryResponse resp = null;
		try {
			// 参数校验
			userInfoQueryServiceParamentChecker.checkQueryUserInfoRequest(request);
			
			PersonMember personMember = personMemberService.queryByMemberId(request.getMemberId());
			// 会员不存在
			if (personMember == null) {
				resp = new IndividualBasicInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
			} else {
				resp = new IndividualBasicInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, personMember);
				resp.setMobile(personMember.getPhoneNo());
			}
			resp.setMemberId(request.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new IndividualBasicInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null) {
				BeanUtilsWrapper.copyProperties(resp, request);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new IndividualBasicInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
		}

		log.info("queryUserInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

	@Override
	public IndividualExtInfoQueryResponse queryIndividualInfo(
			IndividualInfoQueryRequest request) {
		log.info("queryIndividualInfo.req:" + JSONObject.toJSONString(request));
		IndividualExtInfoQueryResponse resp = null;
		try {
			// 参数校验
			userInfoQueryServiceParamentChecker.checkQueryIndividualInfoRequest(request);
			
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("memberId", request.getMemberId());
			criteria.put("customerId", request.getCustomerId());
			
			List<PersonInfo> list = personInfoService.queryByCriteria(criteria);
			// 客户信息对象不存在
			if (list == null || list.isEmpty()) {
				resp = new IndividualExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.CUSTOMER_NOT_FOUND.code(), 
						ResultCode.CUSTOMER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				PersonInfo personInfo = list.get(0);
				resp = new IndividualExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, personInfo);
				resp.setMarriageStatus(personInfo.getMarriagerStatus()); // 表中字段名与接口定义不一致
				if (StringUtils.isEmpty(request.getMemberId())) {
					PersonMember personMember = new PersonMember();
					personMember.setCustomerId(request.getCustomerId());
					personMember = personMemberService.queryPersonMember(personMember);
					resp.setMemberId(personMember.getMemberId());
				} else {
					resp.setMemberId(request.getMemberId());
				}
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new IndividualExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null) {
				BeanUtilsWrapper.copyProperties(resp, request);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new IndividualExtInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
		}
		log.info("queryIndividualInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

	@Override
	public UserAuthInfoQueryResponse queryUserAuthInfo(
			IndividualInfoQueryRequest request) {
		log.info("queryUserAuthInfo.req:" + JSONObject.toJSONString(request));
		UserAuthInfoQueryResponse resp = null;
		try {
			// 参数校验
			userInfoQueryServiceParamentChecker.checkQueryUserAuthInfoRequest(request);
			
			PersonMember personMember = new PersonMember();
			personMember.setMemberId(request.getMemberId());
			personMember.setCustomerId(request.getCustomerId());

			personMember = personMemberService.queryPersonMember(personMember);
			// 对象不存在
			if (personMember == null) {
				resp = new UserAuthInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				resp = new UserAuthInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), 
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, personMember);
				// 认证等级取用户等级串的第一位
				resp.setAuthGrade(Integer.parseInt(personMember.getGrade().substring(0, 1)));
				// 如果已认证
				if (resp.getAuthGrade() != Integer.valueOf(AuthGrade.UNAUTHORIZED.getCode())) {
					PersonInfo personInfo = personInfoService.queryByMemberId(personMember.getMemberId());
					resp.setRealName(personInfo.getPersonName());
					resp.setCertificateType(personInfo.getCertificateType());
					resp.setCertificateNo(personInfo.getCertificateNo());
				}
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new UserAuthInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null)
				BeanUtilsWrapper.copyProperties(resp, request);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new UserAuthInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
		}
		log.info("queryUserAuthInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

}
