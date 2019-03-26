/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.dto;


/**
 * ClassName: MsgSignSendResult <br/>
 * Function: 个人会员查询已绑定银行卡请求参数. <br/>
 * Date: 2016年12月15日 上午9:28:16 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class BindCardQueryReq extends BaseResult{

	/**
	 * memberId:会员Id
	 */
	private String memberId;
	/**
	 * cardIdxNo	银行卡号
	 */
	private String cardIdxNo;
	
	/**
	 * mobile:绑卡手机号
	 */
	private String mobile;
	/**
	 * idCard:	客户证件号
	 */
	private String idCard;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCardIdxNo() {
		return cardIdxNo;
	}
	public void setCardIdxNo(String cardIdxNo) {
		this.cardIdxNo = cardIdxNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
