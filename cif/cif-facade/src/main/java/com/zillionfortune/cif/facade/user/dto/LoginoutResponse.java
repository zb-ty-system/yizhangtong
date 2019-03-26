/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: LoginoutResponse <br/>
 * Function: 会员登出_响应对象. <br/>
 * Date: 2016年11月15日 下午4:35:00 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
public class LoginoutResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private String memberId;

	
	public LoginoutResponse() {
		super();
	}

	public LoginoutResponse(String memberId) {
		super();
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
