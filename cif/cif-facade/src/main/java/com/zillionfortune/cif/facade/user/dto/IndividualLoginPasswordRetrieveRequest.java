/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: IndividualLoginPasswordRetrieveRequest <br/>
 * Function: 个人会员重置登录密码业务Request. <br/>
 * Date: 2016年12月9日 下午3:35:23 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class IndividualLoginPasswordRetrieveRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	
	/**
	 * newPassword:新密码.
	 */
	private String newPassword;

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

	/**
	 * 获取newPassword的值.
	 *
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 设置newPassword的值.
	 *
	 * @param  newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
