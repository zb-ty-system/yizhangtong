/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: IndividualLoginResponse <br/>
 * Function: 个人_会员登入_响应对象. <br/>
 * Date: 2016年11月15日 下午4:30:08 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class IndividualLoginResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员ID
	 * 必填
	 */
	private String memberId;
	
	/**
	 * 访问token
	 * 必填
	 */
	private String accessToken;
	
	public IndividualLoginResponse(String respCode) {
		super(respCode);
	}
	
	public IndividualLoginResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public IndividualLoginResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
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
