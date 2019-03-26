/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: UserAuthServiceRequest <br/>
 * Function: 企业会员实名认证请求对象. <br/>
 * Date: 2016年12月2日 上午10:49:09 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseUserAuthServiceRequest extends BaseRequest{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	/**
	 * certificateType:证件类型.
	 */
	private String certificateType;
	/**
	 * certificateNo:证件号码.
	 */
	private String certificateNo;
	/**
	 * enterpriseName:企业名称
	 */
	private String enterpriseName;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
}
