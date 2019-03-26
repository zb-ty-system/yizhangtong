/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: IndividualLoginAuthResponse <br/>
 * Function: 个人会员登录授权_响应对象. <br/>
 * Date: 2016年11月21日 下午3:49:00 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class IndividualLoginAuthResponse extends BaseResponse {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员Id
	 */
	private String memberId;
	
	public IndividualLoginAuthResponse() {
		super();
	}

	public IndividualLoginAuthResponse(String respCode) {
		super(respCode);
	}
	
	public IndividualLoginAuthResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	
	public IndividualLoginAuthResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
