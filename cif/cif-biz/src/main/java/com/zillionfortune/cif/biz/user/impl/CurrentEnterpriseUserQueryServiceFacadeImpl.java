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
import com.zillionfortune.cif.biz.user.check.CurrentEntUserQueryServiceParameterChecker;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.facade.user.CurrentEnterpriseUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryResponse;
import com.zillionfortune.cif.service.redis.EnterpriseTokenManager;
import com.zillionfortune.cif.service.redis.EnterpriseUserRedisService;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: CurrentEnterpriseUserQueryServiceFacade <br/>
 * Function: 企业会员登录信息查询接口实现. <br/>
 * Date: 2016年11月22日 下午3:35:04 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class CurrentEnterpriseUserQueryServiceFacadeImpl implements
		CurrentEnterpriseUserQueryServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(CurrentEnterpriseUserQueryServiceFacadeImpl.class);
	
	@Autowired
	private EnterpriseUserRedisService enterpriseUserRedisService;
	
	@Autowired
	private EnterpriseTokenManager tokenManagerEnterprise;
	
	@Autowired
	private CurrentEntUserQueryServiceParameterChecker currentEntUserQueryServiceParameterChecker;

	@Override
	public CurrentEnterpriseUserQueryResponse query(CurrentEnterpriseUserQueryRequest request) {
		log.info("query.req:" + JSONObject.toJSONString(request));
		CurrentEnterpriseUserQueryResponse resp = null;
		try {
			// 参数校验
			currentEntUserQueryServiceParameterChecker.checkQueryRequest(request);
			
			String operatorId = request.getOperatorId();
			// 校验token有效性
			EnterpriseTokenModel model = new EnterpriseTokenModel(operatorId, request.getAccessToken());
			boolean rsFlg = tokenManagerEnterprise.checkToken(model);
			if (!rsFlg) {
				resp = new CurrentEnterpriseUserQueryResponse(RespCode.SUCCESS.code(),ResultCode.TOKEN_ERROR.code(),
						ResultCode.TOKEN_ERROR.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				// token 校验通过 则查询redis数据返回
				EnterpriseRedisModel redisModel =  enterpriseUserRedisService.get(operatorId);
				// 未取到数据或者对比请求参数memberId 如若未匹配上 则提示未登录
				if (redisModel == null || !request.getMemberId().equals(redisModel.getMemberId())) {
					resp = new CurrentEnterpriseUserQueryResponse(RespCode.SUCCESS.code(),ResultCode.USER_NOT_LOGIN.code(),
							ResultCode.USER_NOT_LOGIN.desc());
					BeanUtilsWrapper.copyProperties(resp, request);
					log.info("query.resp:" + JSONObject.toJSONString(resp));
					return resp;
				}
				
				resp = new CurrentEnterpriseUserQueryResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, redisModel);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new CurrentEnterpriseUserQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			if (request != null) {
				BeanUtilsWrapper.copyProperties(resp, request);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new CurrentEnterpriseUserQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
		}
		log.info("query.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

}
