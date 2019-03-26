/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: IndividualLoginAuthRequest <br/>
 * Function: 个人_会员登录授权_请求参数对象. <br/>
 * Date: 2016年11月21日 下午3:47:13 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class IndividualLoginAuthRequest extends BaseRequest {
	
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
	 * 访问token
	 * 必输
	 */
	private String accessToken;
	
	public IndividualLoginAuthRequest() {
		super();
	}
	
	public IndividualLoginAuthRequest(String memberId,
			String accessToken) {
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
