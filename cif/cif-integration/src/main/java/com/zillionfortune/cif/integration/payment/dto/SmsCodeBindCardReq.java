/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.dto;


/**
 * ClassName: BindCardRequest <br/>
 * Function: 支付网关绑卡请求对象. <br/>
 * Date: 2016年12月12日 下午3:37:20 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class SmsCodeBindCardReq{

	/** memberId */
	private String memberId;
	/** 证件类型    */
	private Integer certificateType;
	/** 证件号码  */
	private String certificateNo;
	/** 真实姓名   */
	private String realName;
	/** 银行预留手机  */
	private String mobile;
	/** 银行卡号 */
	private String bankcardNo;
	/** 银行编码	  D：借记卡；C：信用卡*/
	private String bankCode;
	/** 银行卡类型	  */
	private String cardType;
	
	/** 短信验证码  */
	private String smsCode;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Integer getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBankcardNo() {
		return bankcardNo;
	}
	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	
}
