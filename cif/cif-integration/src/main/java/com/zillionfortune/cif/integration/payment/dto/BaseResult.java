/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.dto;

/**
 * ClassName: BaseResult <br/>
 * Function: 支付返回结果基类. <br/>
 * Date: 2016年12月15日 上午9:29:57 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class BaseResult {

	/**
	 * respCode:响应状态编码
	 */
	private String respCode;
	/**
	 * resultCode:业务处理结果编码
	 */
	private String resultCode;
	/**
	 * resultMsg:业务处理结果描述
	 */
	private String resultMsg;
	
	/** memberId */
	private String memberId;
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
