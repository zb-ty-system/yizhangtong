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
import com.zillionfortune.cif.biz.user.check.UserGradeServiceParameterChecker;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.UserGradeServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceResponse;
import com.zillionfortune.cif.facade.user.dto.UserGradeUpdateResponse;
import com.zillionfortune.cif.service.user.PersonMemberService;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: UserGradeServiceFacadeImpl <br/>
 * Function: 个人会员等级相关服务实现. <br/>
 * Date: 2016年11月11日 上午9:24:47 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class UserGradeServiceFacadeImpl implements UserGradeServiceFacade {
	private static Logger log = LoggerFactory.getLogger(UserGradeServiceFacadeImpl.class);
	
	@Autowired
	private PersonMemberService personMemberService;
	
	@Autowired
	private UserGradeServiceParameterChecker userGradeServiceParameterChecker;

	@Override
	public UserGradeUpdateResponse updateGrade(UserGradeServiceRequest request) {
		log.info("updateGrade.req:" + JSONObject.toJSONString(request));
		UserGradeUpdateResponse resp = null;
		try {
			// 参数校验
			userGradeServiceParameterChecker.checkUpdateGradeRequest(request);
			
			PersonMember member = personMemberService.queryByMemberId(request.getMemberId());
			// 会员不存在
			if (null == member) {
				resp = new UserGradeUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
			} else {
				personMemberService.updateGrade(request.getMemberId(), member.getGrade(),
						request.getGrade(), Integer.valueOf(request.getGradeType()));
				resp = new UserGradeUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			}
			resp.setMemberId(request.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new UserGradeUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null) {
				resp.setMemberId(request.getMemberId());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new UserGradeUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			resp.setMemberId(request.getMemberId());
		}
		log.info("updateGrade.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

	@Override
	public UserGradeServiceResponse queryGrade(UserGradeServiceRequest request) {
		log.info("queryGrade.req:" + JSONObject.toJSONString(request));
		UserGradeServiceResponse resp = null;
		try {
			// 参数校验
			userGradeServiceParameterChecker.checkQueryGradeRequest(request);

			String grade = personMemberService.getGrade(request.getMemberId(),Integer.valueOf(request.getGradeType()));
			// 用户不存在
			if (grade == null) {
				resp = new UserGradeServiceResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(), 
						ResultCode.USER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				resp = new UserGradeServiceResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
				resp.setGrade(grade);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new UserGradeServiceResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null) {
				BeanUtilsWrapper.copyProperties(resp, request);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new UserGradeServiceResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
		}
		log.info("queryGrade.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

}
