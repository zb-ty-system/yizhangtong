/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseLoginPasswordRetrieveRequest <br/>
 * Function: 企业会员重置登录密码业务Request. <br/>
 * Date: 2016年12月14日 下午5:08:17 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginPasswordRetrieveRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * customerNo:商户号.
	 */
	private String customerNo;
	
	/**
	 * userName:用户名.
	 */
	private String userName;
	
	/**
	 * newPassword:新密码.
	 */
	private String newPassword;

	/**
	 * 获取customerNo的值.
	 *
	 * @return customerNo
	 */
	public String getCustomerNo() {
		return customerNo;
	}

	/**
	 * 设置customerNo的值.
	 *
	 * @param  customerNo
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	/**
	 * 获取userName的值.
	 *
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置userName的值.
	 *
	 * @param  userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
