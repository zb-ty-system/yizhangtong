package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class EnterpriseRegisterOpertorRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 邮箱
	 * email和mobile不能同时为空
	 */
	private String email;
	
	/**
	 * 邮箱
	 * email和mobile不能同时为空
	 */
	private String mobile;
	
	/**
	 * 登录密码
	 * 必输
	 * 在原文的基础上MD5 32位加密后转换为大写
	 */
	private String password;
	
	/**
	 * 商户号
	 * 必输
	 */
	private String customerNo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	
}
