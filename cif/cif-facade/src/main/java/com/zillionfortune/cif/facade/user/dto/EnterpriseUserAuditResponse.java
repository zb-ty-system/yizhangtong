/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;


/**
 * ClassName: EnterpriseUserAuditResponse <br/>
 * Function: 企业会员认证信息审核接口_响应. <br/>
 * Date: 2016年12月27日 下午5:53:21 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseUserAuditResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/** 会员编号  必输 */
	private String memberId;
	
	/**
	 * authGrade:认证等级
	 */
	private String authGrade;

	public EnterpriseUserAuditResponse() {
		super();
	}

	public EnterpriseUserAuditResponse(String respCode,String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseUserAuditResponse(String respCode,String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseUserAuditResponse(String respCode) {
		super(respCode);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAuthGrade() {
		return authGrade;
	}

	public void setAuthGrade(String authGrade) {
		this.authGrade = authGrade;
	}

}
