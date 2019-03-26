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
import com.zillionfortune.cif.biz.user.check.CurrentUserQueryParameterChecker;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.CurrentUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceResponse;
import com.zillionfortune.cif.service.redis.TokenManager;
import com.zillionfortune.cif.service.redis.UserRedisService;
import com.zillionfortune.cif.service.redis.model.IndividualRedisModel;
import com.zillionfortune.cif.service.redis.model.TokenModel;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: CurrentUserQueryServiceFacadeImpl <br/>
 * Function: 个人会员登录信息查询接口_实现. <br/>
 * Date: 2016年11月15日 下午5:06:53 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
@Component
public class CurrentUserQueryServiceFacadeImpl implements
		CurrentUserQueryServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(CurrentUserQueryServiceFacadeImpl.class);
	
	@Autowired
	private UserRedisService userRedisService;
	
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private CurrentUserQueryParameterChecker currentUserQueryParameterChecker;

	@Override
	public CurrentUserQueryServiceResponse query(
			CurrentUserQueryServiceRequest req) {
		log.info("query.req:" + JSONObject.toJSONString(req));
		CurrentUserQueryServiceResponse resp = null;
		try {
			// 参数校验
			currentUserQueryParameterChecker.checkQueryRequest(req);
			// 校验token有效性
			boolean rsFlg = tokenManager.checkToken(new TokenModel(req.getMemberId(), req.getAccessToken()));
			if (!rsFlg) {
				resp = new CurrentUserQueryServiceResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),
						ResultCode.TOKEN_ERROR.desc());
				BeanUtilsWrapper.copyProperties(resp, req);
			} else {
				// token 校验通过 则查询redis数据返回
				IndividualRedisModel redisModel =  userRedisService.get(req.getMemberId());
				// 查不到信息则 返回用户未登录
				if (redisModel == null || StringUtils.isEmpty(redisModel.getUserName())) {
					resp = new CurrentUserQueryServiceResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_LOGIN.code(),
							ResultCode.USER_NOT_LOGIN.desc());
					BeanUtilsWrapper.copyProperties(resp, req);
					log.info("query.resp:" + JSONObject.toJSONString(resp));
					return resp;
				}
				
				resp = new CurrentUserQueryServiceResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, redisModel);
				resp.setMobile(redisModel.getPhoneNo());
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new CurrentUserQueryServiceResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (req != null) {
				BeanUtilsWrapper.copyProperties(resp, req);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new CurrentUserQueryServiceResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, req);
		}
		log.info("query.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

}
