/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseLoginAuthResponse <br/>
 * Function: 企业_会员登录鉴权_响应对象. <br/>
 * Date: 2016年11月21日 下午3:49:00 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginAuthResponse extends BaseResponse {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员ID
	 * 必填
	 */
	private String memberId;
	
	/**
	 * 操作员ID
	 * 必填
	 */
	private String operatorId;
	
	/**
	 * 企业名称
	 */
	private String enterperseName;
	
	/**
	 * 操作员手机号
	 */
	private String mobile;
	
	/**
	 * 会员认证状态
	 * 0：待认证；1：认证中；2：认证失败；3：已认证
	 */
	private String authStatus;
	
	
	
	public EnterpriseLoginAuthResponse(String respCode) {
		super(respCode);
	}
	
	public EnterpriseLoginAuthResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseLoginAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnterpriseLoginAuthResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getEnterperseName() {
		return enterperseName;
	}

	public void setEnterperseName(String enterperseName) {
		this.enterperseName = enterperseName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

}
