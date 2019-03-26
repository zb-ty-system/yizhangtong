/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.common.util.BeanConvertUtil;
import com.zillionfortune.cif.common.util.HttpClientUtil;
import com.zillionfortune.cif.integration.payment.PaymentFrontService;
import com.zillionfortune.cif.integration.payment.dto.BaseResult;
import com.zillionfortune.cif.integration.payment.dto.BindCardQueryReq;
import com.zillionfortune.cif.integration.payment.dto.BindCardQueryResult;
import com.zillionfortune.cif.integration.payment.dto.SmsCodeBindCardReq;
import com.zillionfortune.cif.integration.payment.dto.SmsCodeBindCardResult;

/**
 * ClassName: PaymentFrontService <br/>
 * Function: 支付网关服务实现. <br/>
 * Date: 2016年12月15日 上午9:16:08 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class PaymentFrontServiceImpl implements PaymentFrontService{
	/**
	 * payUrl: 支付网关url
	 */
	@Value("${payment.url}")
	private String payUrl;
	
	/**
	 * msgSignSend:短信签约发送. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public SmsCodeBindCardResult msgSignSend(SmsCodeBindCardReq req) throws IOException {
		Map<String, String> paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
		String rsJson = HttpClientUtil.doPost(payUrl + "msgSignSend.json", paramMap);
		return JSONObject.parseObject(rsJson, SmsCodeBindCardResult.class);
	}
	
	/**
	 * msgSignConfirm:短信签约确认. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public SmsCodeBindCardResult msgSignConfirm(SmsCodeBindCardReq req) throws IOException {
		Map<String, String> paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
		String rsJson = HttpClientUtil.doPost(payUrl + "msgSignConfirm.json", paramMap);
		return JSONObject.parseObject(rsJson, SmsCodeBindCardResult.class);
	}
	
	/**
	 * unBindCard:解绑银行卡接口. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BaseResult unBindCard(SmsCodeBindCardReq req) throws IOException {
		Map<String, String> paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
		String rsJson = HttpClientUtil.doPost(payUrl + "unBindCard.json", paramMap);
		return JSONObject.parseObject(rsJson, BaseResult.class);
	}

	/**
	 * queryBindCard:解绑银行卡接口. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BindCardQueryResult queryBindCard(BindCardQueryReq req) throws IOException {
		Map<String, String> paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
		String rsJson = HttpClientUtil.doPost(payUrl + "queryBindCard.json", paramMap);
		return JSONObject.parseObject(rsJson, BindCardQueryResult.class);
	}
}
