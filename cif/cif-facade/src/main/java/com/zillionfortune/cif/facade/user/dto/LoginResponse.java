/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: LoginResponse <br/>
 * Function: 会员登入_响应对象. <br/>
 * Date: 2016年11月15日 下午4:30:08 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class LoginResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String accessToken;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(String memberId, String accessToken) {
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
