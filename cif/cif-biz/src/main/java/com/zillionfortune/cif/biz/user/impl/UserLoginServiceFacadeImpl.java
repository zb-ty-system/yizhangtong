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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.biz.user.check.IndividualLoginServiceParamentChecker;
import com.zillionfortune.cif.common.enums.LoginStatusCodeEnum;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.DateFormat;
import com.zillionfortune.cif.common.util.JsonUtils;
import com.zillionfortune.cif.dal.entity.PersonLoginLog;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.UserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginResponse;
import com.zillionfortune.cif.service.redis.TokenManager;
import com.zillionfortune.cif.service.redis.UserRedisService;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;
import com.zillionfortune.cif.service.redis.model.IndividualRedisModel;
import com.zillionfortune.cif.service.redis.model.TokenModel;
import com.zillionfortune.cif.service.user.PersonLoginLogService;
import com.zillionfortune.cif.service.user.PersonMemberService;

/**
 * ClassName: UserLoginServiceFacadeImpl <br/>
 * Function: 个人_会员登录接口_实现. <br/>
 * Date: 2016年11月15日 下午5:01:18 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Service
public class UserLoginServiceFacadeImpl implements UserLoginServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(UserLoginServiceFacadeImpl.class);
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private UserRedisService userRedisService;
	
	@Autowired
	private PersonMemberService userService;
	
	@Autowired
	private PersonLoginLogService personLoginLogService;
	
	@Autowired
	IndividualLoginServiceParamentChecker paraChecker;
	
	
	@Override
	public IndividualLoginResponse login(IndividualLoginRequest req) {
		log.info("UserLoginServiceFacadeImpl.auth.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginResponse resp = null;
		String memberId = null;
		IndividualRedisModel individualRedisModel = null;
		TokenModel tokenModel = null;
		
		try {
			//请求参数校验
			paraChecker.checkIndividualLoginRequest(req);
			
			//校验userName
			PersonMember personMember = new PersonMember();
			personMember.setUserName(req.getUserName());
			personMember = userService.queryPersonMember(personMember);
			if(null==personMember){
				resp = new IndividualLoginResponse(RespCode.SUCCESS.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.desc());
				log.info("UserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验password（当登入输入密码时）
			if(!StringUtils.isBlank(req.getPassword()) ){
				if(!req.getPassword().toUpperCase().equals(personMember.getPassword()) ){
					resp = new IndividualLoginResponse(RespCode.SUCCESS.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.desc());
					log.info("UserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
					return resp ;
				}
			}
			
			//判断会员状态（若为冻结、注销，则不允许登录）
			int status = personMember.getStatus();
			if(status==UserStatusCodeEnum.FROZEN.getCode() || status==UserStatusCodeEnum.CANCEL.getCode()){
				resp = new IndividualLoginResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.desc());
				log.info("UserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//创建accessToken并设置有效时间（redis）
			memberId = personMember.getMemberId();//key
			String tokenStr = null;
			if(!tokenManager.checkToken(memberId)){
				tokenModel = tokenManager.createToken(memberId);
				tokenStr = tokenModel.getToken();
			}
			tokenStr = tokenManager.getTokenByKey(memberId);
			
			//判断登入来源,若不同则删除token新建token
			if(userRedisService.check(memberId)){//判断用户信息是否存在
				individualRedisModel = userRedisService.get(memberId);
				if(individualRedisModel.getLoginSource()!=Integer.parseInt(req.getLoginSource())){//判断登入来源是否相同
					//token是否删除成功
					tokenManager.deleteToken(memberId);
					//新建token
					tokenModel = tokenManager.createToken(memberId);
					tokenStr = tokenModel.getToken();
					//更新企业信息
					individualRedisModel.setLoginSource(Integer.parseInt(req.getLoginSource()));
					userRedisService.update(individualRedisModel);
				}
			}else{
				//添加个人用户信息并设置有效时间（redis）
				individualRedisModel = new IndividualRedisModel();
				individualRedisModel.setMemberId(memberId);//key
				individualRedisModel.setLoginSource(Integer.parseInt(req.getLoginSource()));//登入来源
				userRedisService.add(individualRedisModel);
			}
			
			//组装响应对象
			resp = new IndividualLoginResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			resp.setAccessToken(tokenStr);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new IndividualLoginResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new IndividualLoginResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("UserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}
	
	@Override
	public IndividualLoginAuthResponse auth(IndividualLoginAuthRequest req) {
		log.info("UserLoginServiceFacadeImpl.login.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginAuthResponse resp = null;
		PersonMember personMember = null;
		String memberId = null;
		String accessToken = null;
		
		try {
			//校验请求参数
			paraChecker.checkIndividualLoginAuthRequest(req);
			
			//获取请求参数
			memberId = req.getMemberId();
			accessToken = req.getAccessToken();
			
			//判断会员状态（若为冻结、注销，则不允许登录）
			personMember = userService.queryByMemberId(memberId);
			if(null==personMember){
				resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.MEMBERID_NO_EXISTS.code(),ResultCode.MEMBERID_NO_EXISTS.desc());
				log.info("UserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			int status = personMember.getStatus();
			if(status==UserStatusCodeEnum.FROZEN.getCode() || status==UserStatusCodeEnum.CANCEL.getCode()){
				resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.desc());
				log.info("UserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken是否失效
			if(StringUtils.isBlank(tokenManager.getTokenByKey(memberId)) ){
				resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_NOT_FOUND.code(),ResultCode.TOKEN_NOT_FOUND.desc());
				log.info("UserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken是否正确
			if(!tokenManager.checkToken(new TokenModel(memberId,accessToken)) ){
				resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),ResultCode.TOKEN_ERROR.desc());
				log.info("UserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//更新个人用户信息（redis）
			if(userRedisService.check(memberId)){
				IndividualRedisModel individualRedisModel = userRedisService.get(memberId);
				individualRedisModel.setMemberId(memberId);//会员Id
				individualRedisModel.setPhoneNo(personMember.getPhoneNo());//联系手机号
				individualRedisModel.setUserName(personMember.getUserName());//用户名
				individualRedisModel.setEmail(personMember.getEmail());//邮箱
				individualRedisModel.setLoginStatus(LoginStatusCodeEnum.loginYes.getCode());//登入状态
				individualRedisModel.setLoginTime(DateFormat.fullFormat(new Date()));
				userRedisService.update(individualRedisModel);
			}
			
			//插入登入日志
			personLoginLogService.insert(new PersonLoginLog(personMember.getUserName(),new Date()));
			
			//组装响应对象
			resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new IndividualLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new IndividualLoginAuthResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("UserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}
	
	@Override
	public IndividualLoginOutResponse loginout(IndividualLoginOutRequest req) {
		log.info("UserLoginServiceFacadeImpl.loginout.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginOutResponse resp = null;
		String memberId = null;
		String accessToken = null;
		
		try {
			//请求参数校验
			paraChecker.checkIndividualLoginoutRequest(req);
			
			//获取请求参数
			memberId = req.getMemberId();
			accessToken = req.getAccessToken();
			
			//校验memberId
			PersonMember personMember = userService.queryByMemberId(memberId);
			if(null==personMember){
				resp = new IndividualLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.MEMBERID_NO_EXISTS.code(),ResultCode.MEMBERID_NO_EXISTS.desc());
				log.info("UserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken
			if(!tokenManager.checkToken(new TokenModel(memberId,accessToken)) ){
				resp = new IndividualLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),ResultCode.TOKEN_ERROR.desc());
				log.info("UserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//删除accessToken
			tokenManager.deleteToken(memberId);
			
			//删除个人用户信息
			userRedisService.delete(memberId);
			
			//组装响应对象
			resp = new IndividualLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new IndividualLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new IndividualLoginOutResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("UserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}
	
}
