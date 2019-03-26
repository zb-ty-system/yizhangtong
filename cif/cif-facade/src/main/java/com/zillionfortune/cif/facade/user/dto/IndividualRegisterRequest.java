package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class IndividualRegisterRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 
	 * 手机号  
	 * 必输   
	 */
	private String mobile;
	
	/**
	 * 注册来源
	 * 1：pc端；2：Android客户端；3：IOS客户端
	 */
	private String registerSource;
	
	
	/**
	 * 登录密码   
	 * 必输
	 * 在原文的基础上MD5加密后转换为大写
	 */
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
