/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: BindCardQueryRequest <br/>
 * Function: 个人会员绑定卡信息查询请求对象. <br/>
 * Date: 2016年12月13日 下午2:09:26 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class BindCardQueryRequest extends BaseRequest{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/** memberId */
	private String memberId;
	/** 证件类型    */
	private Integer certificateType;
	/** 证件号码  */
	private String certificateNo;
	/** 银行卡号 */
	private String bankcardNo;
	
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
	public String getBankcardNo() {
		return bankcardNo;
	}
	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}
}
