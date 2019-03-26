/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseLoginAuthRequest <br/>
 * Function: 企业_会员登录鉴权_请求参数对象. <br/>
 * Date: 2016年11月21日 下午3:47:13 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginAuthRequest extends BaseRequest {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 * 必输
	 */
	private String memberId;
	
	/**
	 * 操作员Id
	 * 必输
	 */
	private String operatorId;
	
	/**
	 * 访问token
	 * 必输
	 */
	private String accessToken;
	
	public EnterpriseLoginAuthRequest() {
		super();
	}
	
	public EnterpriseLoginAuthRequest(String memberId, String operatorId,
			String accessToken) {
		super();
		this.memberId = memberId;
		this.operatorId = operatorId;
		this.accessToken = accessToken;
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
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
