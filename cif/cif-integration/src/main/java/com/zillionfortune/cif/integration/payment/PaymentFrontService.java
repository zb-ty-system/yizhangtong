/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.integration.payment.dto.BaseResult;
import com.zillionfortune.cif.integration.payment.dto.BindCardQueryReq;
import com.zillionfortune.cif.integration.payment.dto.SmsCodeBindCardReq;

/**
 * ClassName: PaymentFrontService <br/>
 * Function: 支付网关服务接口. <br/>
 * Date: 2016年12月15日 上午9:16:08 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public interface PaymentFrontService {
	
	/**
	 * msgSignSend:短信签约发送. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BaseResult msgSignSend(SmsCodeBindCardReq req) throws IOException;
	
	/**
	 * msgSignConfirm:短信签约确认. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BaseResult msgSignConfirm(SmsCodeBindCardReq req) throws IOException ;
	
	/**
	 * unBindCard:解绑银行卡接口. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BaseResult unBindCard(SmsCodeBindCardReq req) throws IOException ;

	/**
	 * queryBindCard:解绑银行卡接口. <br/>
	 *
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public BaseResult queryBindCard(BindCardQueryReq req) throws IOException;
}
