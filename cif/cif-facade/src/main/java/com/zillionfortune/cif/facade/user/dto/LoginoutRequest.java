/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: LoginoutRequest <br/>
 * Function: 会员登出_请求参数对象. <br/>
 * Date: 2016年11月15日 下午4:29:27 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class LoginoutRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String accessToken;
	
	public LoginoutRequest() {
		super();
	}

	public LoginoutRequest(String memberId, String accessToken) {
		super();
		this.memberId = memberId;
		this.accessToken = accessToken;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
