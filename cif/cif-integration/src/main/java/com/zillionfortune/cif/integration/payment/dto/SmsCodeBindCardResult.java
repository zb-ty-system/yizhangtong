/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.dto;

/**
 * ClassName: MsgSignSendResult <br/>
 * Function: 短信签约发送确认请求结果. <br/>
 * Date: 2016年12月15日 上午9:28:16 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class SmsCodeBindCardResult extends BaseResult{

	/**
	 * signId:签约流水Id
	 */
	private String signId;

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
	
}
