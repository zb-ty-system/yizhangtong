/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.biz.user.check.EnterpriseLoginServiceParamentChecker;
import com.zillionfortune.cif.common.enums.LoginStatusCodeEnum;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.DateFormat;
import com.zillionfortune.cif.common.util.JsonUtils;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseLoginLog;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.facade.user.EnterpriseUserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginResponse;
import com.zillionfortune.cif.service.ParameterConfigService;
import com.zillionfortune.cif.service.redis.EnterpriseTokenManager;
import com.zillionfortune.cif.service.redis.EnterpriseUserRedisService;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseLoginLogService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;

/**
 * ClassName: UserLoginServiceFacadeImpl <br/>
 * Function: 企业_会员登录接口_实现. <br/>
 * Date: 2016年11月15日 下午5:01:18 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Service
public class EnterpriseUserLoginServiceFacadeImpl implements EnterpriseUserLoginServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(EnterpriseUserLoginServiceFacadeImpl.class);
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private EnterpriseTokenManager tokenManager;
	
	@Autowired
	EnterpriseUserRedisService enterpriseUserRedisService;
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	private EnterpriseLoginLogService enterpriseLoginLogService;
	
	@Autowired
	private ParameterConfigService parameterConfigService;
	
	@Autowired
	EnterpriseLoginServiceParamentChecker paraChecker;
	
	@Value("${auth.isopen}")
    private String IS_OPEN ;
	
	@Value("${auth.max_try_count}")
    private int MAX_TRY_COUNT = 0;
	
    @Value("${auth.max_disabled_seconds}")
    private int MAX_DISABLED_SECONDS = 0;
	
	
	
	@Override
	public EnterpriseLoginResponse login(EnterpriseLoginRequest req) {
		log.info("EnterpriseUserLoginServiceFacadeImpl.login.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginResponse resp = null;
		EnterpriseOperator enterpriseOperator = null;
		String password = null;
		String loginSource = null;
		EnterpriseTokenModel tokenModel = null;
		EnterpriseRedisModel enterpriseRedisModel = null;
		
		try {
			//请求参数校验
			paraChecker.checkEnterpriseLoginRequest(req);
			
			//获取请求参数
			password = req.getPassword();
			loginSource = req.getLoginSource();
			
			//根据商户号、登入名，校验操作员
			enterpriseOperator = enterpriseOperatorService.queryEnterpriseOperator(req.getCustomerNo(), req.getLoginName());
			if(null==enterpriseOperator){
				resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.CUSTOMERNO_OR_USERNAME_ERROR.code(),ResultCode.CUSTOMERNO_OR_USERNAME_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			// ===========================================================
			//  					登入访问限制 begin 
			// ===========================================================
			if("true".equalsIgnoreCase(IS_OPEN)){
				// 根据用户名获取最近 MAX_DISABLED_SECONDS 秒内失败的次数，是否超过最大限制 MAX_TRY_COUNT
				String key = req.getLoginName();
				String countString = redisTemplate.opsForValue().get(key);
				boolean exists = countString != null;
				int count = exists ? Integer.parseInt(countString) : 0;
				if (count >= MAX_TRY_COUNT) {// 若超过最大限制，不再对用户名和密码进行认证，直接返回认证失败提示信息，也即账户已被锁定的提示信息,否则，进行用户认证
					resp = checkoutMessage(key, count);
					return resp;
		        }
				if (!password.toUpperCase().equals(enterpriseOperator.getPassword()) ) {// 若认证失败，将其添加到 Redis 缓存中，并设置过期默认为 MAX_DISABLED_SECONDS，表示从此刻起，MAX_DISABLED_SECONDS 秒内，该用户已登录失败 count 次
		            count++;
		            if (exists) { // 若Redis缓存中已存在该用户认证失败的计数信息，则刷新 count 值，并将旧值的剩余存活时间设置到新值上，然后返回认证失败提示信息
		            	RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		                counter.getAndAdd(1);
		                if (count >= MAX_TRY_COUNT) {
		                    redisTemplate.expire(key, MAX_DISABLED_SECONDS, TimeUnit.SECONDS);
		                }
		            } else { // 若Redis缓存中不存在该用户认证失败的计数信息，则创建，并设置存活时间，然后返回认证失败提示信息
		                redisTemplate.opsForValue().set(key, count + "");
		                redisTemplate.expire(key, MAX_DISABLED_SECONDS, TimeUnit.SECONDS);
		            }
		            resp = checkoutMessage(key, count);
		            return resp;
		        }
				// 否则，返回认证成功提示信息
		        count = 0;
		        if (exists) {
		            redisTemplate.delete(key);
		        }
		        resp = checkoutMessage(key, count);
		        if (!(RespCode.SUCCESS.code()).equals(resp.getRespCode())
						|| !(ResultCode.SUCCESS.code()).equals(resp.getResultCode())) {
		        	resp = new EnterpriseLoginResponse(resp.getRespCode(),resp.getResultCode(),resp.getResultMsg());
					log.info("UserLoginBizImpl.login.resp:" + JsonUtils.object2Json(resp));
					return resp;
		        }
			}else{
				//校验password
				if(!StringUtils.isBlank(password)){
					if(!password.toUpperCase().equals(enterpriseOperator.getPassword()) ){
						resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.code(),ResultCode.USERNAME_OR_PASSWORD_ERROR.desc());
						log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
						return resp;
					}
				}
			}
	     // ===========================================================
		 //  					end 登录访问限制  
		 // ===========================================================
	        
			
			//判断会员和操作员状态（若为冻结、注销状态，则不允许登录）
			EnterpriseMember enterpriseMember = enterpriseMemberService.queryByMemberId(enterpriseOperator.getMemberId());
			int memberStatus = enterpriseMember.getStatus();
			if(memberStatus==UserStatusCodeEnum.FROZEN.getCode() || memberStatus==UserStatusCodeEnum.CANCEL.getCode()){//会员状态判断
				resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.code(),ResultCode.CAN_NOT_LOGIN_MEMBER.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			int operatorStatus = enterpriseOperator.getStatus();
			if(operatorStatus==UserStatusCodeEnum.FROZEN.getCode() || operatorStatus==UserStatusCodeEnum.CANCEL.getCode()){//操作员状态判断
				resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_LOGIN_OPERATOR.code(),ResultCode.CAN_NOT_LOGIN_OPERATOR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//获取操作员主键
			String operatorId = Long.toString(enterpriseOperator.getId());
			
			//创建accessToken并设置有效时间（redis）
			String tokenStr = null;
			if(!tokenManager.checkToken(operatorId)){//检查token是否存在
				tokenModel = tokenManager.createToken(operatorId);
				tokenStr = tokenModel.getToken();
			}
			tokenStr = tokenManager.getTokenByKey(operatorId);
			
			//判断登入来源,若不同则删除token新建token
			if(enterpriseUserRedisService.check(operatorId)){//判断用户信息是否存在
				enterpriseRedisModel = enterpriseUserRedisService.get(operatorId);
				if(enterpriseRedisModel.getLoginSource()!=Integer.parseInt(req.getLoginSource())){//判断登入来源是否相同
					//token是否删除成功
					tokenManager.deleteToken(operatorId);
					//新建token
					tokenModel = tokenManager.createToken(operatorId);
					tokenStr = tokenModel.getToken();
					//更新企业信息
					enterpriseRedisModel.setLoginSource(Integer.parseInt(req.getLoginSource()));
					enterpriseUserRedisService.update(enterpriseRedisModel);
				}
			}else{
				//添加企业用户信息（redis）
				enterpriseRedisModel = new EnterpriseRedisModel();
				enterpriseRedisModel.setOperatorId(operatorId);//key
				enterpriseRedisModel.setLoginSource(Integer.parseInt(loginSource));//登入来源
				enterpriseUserRedisService.add(enterpriseRedisModel);
			}
			
			//组装响应对象
			resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(enterpriseOperator.getMemberId());//会员ID
			resp.setOperatorId(operatorId);//操作员Id
			resp.setAccessToken(tokenStr);//访问token
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new EnterpriseLoginResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}
	
	/**
	 * checkoutMessage:校验出登入访问限制的提示信息. <br/>
	 *
	 * @param key userName
	 * @param count 尝试次数
	 * @return EnterpriseLoginResponse
	 */
    private EnterpriseLoginResponse checkoutMessage(String key, int count) {
    	EnterpriseLoginResponse resp = null;
    	String errorMsg = null;
        if (count == 0) {
            resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			return resp;
        }
        
        if (count >= MAX_TRY_COUNT) {
            long pttlSeconds = redisTemplate.getExpire(key);//单位：秒
            long hours = pttlSeconds / 3600;
            long sencondsRemain = pttlSeconds - hours * 3600;
            long minutes = sencondsRemain / 60;
            long seconds = sencondsRemain - minutes * 60;
            
            errorMsg = "登录超过" + MAX_TRY_COUNT + "次，请" + hours + "小时" + minutes + "分" + seconds + "秒后再试！";
        }else{
        	
        	errorMsg = "密码错误，您还有 " + (MAX_TRY_COUNT - count) + " 次机会！";
        }
        
        resp = new EnterpriseLoginResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),errorMsg);
		return resp;
    }

	@Override
	public EnterpriseLoginAuthResponse auth(EnterpriseLoginAuthRequest req) {
		log.info("EnterpriseUserLoginServiceFacadeImpl.auth.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginAuthResponse resp = null ;
		EnterpriseMember enterpriseMember = null;
		EnterpriseOperator enterpriseOperator = null;
		String memberId = null;
		String accessToken = null;
		String operatorId = null;
		try {
			//请求参数校验
			paraChecker.checkEnterpriseLoginRequest(req);
			
			//获取请求参数
			memberId = req.getMemberId();
			accessToken = req.getAccessToken();
			operatorId = req.getOperatorId();
			
			//根据memberId，查企业会员表
			enterpriseMember = enterpriseMemberService.queryByMemberId(memberId);
			if(null==enterpriseMember){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.MEMBERID_NO_EXISTS.code(),ResultCode.MEMBERID_NO_EXISTS.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//根据operatorId，查操作员表
			enterpriseOperator = enterpriseOperatorService.queryByOperatorId(Long.parseLong(operatorId));
			if(null==enterpriseOperator){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.OPERATERID_ERROR.code(),ResultCode.OPERATERID_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//判断会员和操作员状态（若为冻结、注销状态，则不允许登录）
			int memberStatus = enterpriseMember.getStatus();
			if(memberStatus==UserStatusCodeEnum.FROZEN.getCode() || memberStatus==UserStatusCodeEnum.CANCEL.getCode()){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_AUTH_MEMBER.code(),ResultCode.CAN_NOT_AUTH_MEMBER.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			int opertatorStatus = enterpriseOperator.getStatus();
			if(opertatorStatus==UserStatusCodeEnum.FROZEN.getCode() || opertatorStatus==UserStatusCodeEnum.CANCEL.getCode()){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.CAN_NOT_AUTH_OPERATOR.code(),ResultCode.CAN_NOT_AUTH_OPERATOR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken是否失效
			if(StringUtils.isBlank(tokenManager.getTokenByKey(operatorId)) ){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_NOT_FOUND.code(),ResultCode.TOKEN_NOT_FOUND.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//判断accessToken是否正确
			if(!tokenManager.checkToken(new EnterpriseTokenModel(operatorId,accessToken)) ){
				resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),ResultCode.TOKEN_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//根据customerID查，企业信息表
			EnterpriseInfo enterpriseInfo = enterpriseInfoService.queryByCustomerId(enterpriseMember.getCustomerId());
			
			//更新企业用户信息（redis）
			if(enterpriseUserRedisService.check(operatorId)){
				EnterpriseRedisModel enterpriseRedisModel = enterpriseUserRedisService.get(operatorId);
				enterpriseRedisModel.setMemberId(memberId);//memberId
				enterpriseRedisModel.setOperatorId(operatorId);//操作员ID
				enterpriseRedisModel.setEnterpriseName(enterpriseInfo.getEnterpriseName());//企业名称
				enterpriseRedisModel.setMobile(enterpriseOperator.getMobile());//手机号
				enterpriseRedisModel.setUserName(enterpriseOperator.getUserName());//用户名
				enterpriseRedisModel.setEmail(enterpriseOperator.getEmail());//邮箱
				enterpriseRedisModel.setLoginStatus(LoginStatusCodeEnum.loginYes.getCode());//登入状态
				enterpriseRedisModel.setLoginTime(DateFormat.fullFormat(new Date()));
				enterpriseUserRedisService.update(enterpriseRedisModel);
			}
			
			//插入登入日志
			enterpriseLoginLogService.insert(new EnterpriseLoginLog(enterpriseOperator.getUserName(),enterpriseMember.getCustomerNo(),new Date()));
			
			//组装响应对象
			resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			resp.setOperatorId(operatorId);
			resp.setEnterperseName(enterpriseInfo.getEnterpriseName());
			resp.setMobile(enterpriseOperator.getMobile());//操作员手机号
			resp.setAuthStatus(enterpriseMember.getGrade().substring(0,1));//会员认证状态
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new EnterpriseLoginAuthResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new EnterpriseLoginAuthResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("EnterpriseUserLoginServiceFacadeImpl.auth.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}

	@Override
	public EnterpriseLoginOutResponse loginout(EnterpriseLoginOutRequest req) {
		log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginOutResponse resp = null ;
		String memberId = null;
		String operatorId = null;
		String accessToken = null;
		
		try {
			//请求参数校验
			paraChecker.checkEnterpriseLoginoutRequest(req);
			
			//获取请求参数
			memberId = req.getMemberId();
			operatorId = req.getOperatorId();
			accessToken = req.getAccessToken();
			
			//根据operatorId，查操作员表
			EnterpriseOperator enterpriseOperator = enterpriseOperatorService.queryByOperatorId(Long.parseLong(operatorId));
			if(null==enterpriseOperator){
				resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.OPERATERID_ERROR.code(),ResultCode.OPERATERID_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.login.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken是否失效
			if(StringUtils.isBlank(tokenManager.getTokenByKey(operatorId)) ){
				resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_NOT_FOUND.code(),ResultCode.TOKEN_NOT_FOUND.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验accessToken
			if(!tokenManager.checkToken(new EnterpriseTokenModel(operatorId,accessToken)) ){
				resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),ResultCode.TOKEN_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//校验memberId（测试要求）
			if(!memberId.equals(enterpriseOperator.getMemberId())){
				resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.MEMBERID_ERROR.code(),ResultCode.MEMBERID_ERROR.desc());
				log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
				return resp;
			}
			
			//删除TONKEN
			tokenManager.deleteToken(operatorId);
			
			//删除 企业用户信息redis
			enterpriseUserRedisService.delete(operatorId);
			
			//组装响应对象
			resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			resp.setOperatorId(operatorId);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				BusinessException be = (BusinessException) e;
                resp = new EnterpriseLoginOutResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),be.getMessage());
            } else {
                resp = new EnterpriseLoginOutResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("EnterpriseUserLoginServiceFacadeImpl.loginout.resp:" + JsonUtils.object2Json(resp));
		
		return resp;
	}

}
