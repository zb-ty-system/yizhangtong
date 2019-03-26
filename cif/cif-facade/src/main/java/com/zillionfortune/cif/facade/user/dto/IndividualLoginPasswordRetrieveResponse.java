/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * 个人会员重置登录密码业务Response
 * 
 * @author wangzinan
 *
 */
public class IndividualLoginPasswordRetrieveResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	
	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultCode
	 * @param resultMsg
	 */
	public IndividualLoginPasswordRetrieveResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultMsg
	 */
	public IndividualLoginPasswordRetrieveResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取memberId的值.
	 *
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置memberId的值.
	 *
	 * @param  memberId
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
