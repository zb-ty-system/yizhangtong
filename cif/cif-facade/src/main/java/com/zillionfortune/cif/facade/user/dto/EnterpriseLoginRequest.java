/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: LoginRequest <br/>
 * Function: 企业_会员登入_请求参数对象. <br/>
 * Date: 2016年11月15日 下午4:31:30 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商户号
	 * 必填
	 */
	private String customerNo;
	
	/**
	 * 用户名
	 */
	private String loginName;
	
	/**
	 * 登录密码	  
	 */
	private String password;
	
	/**
	 * 登录来源
	 * 必填
	 * 1：PC端；2：Android端；3：IOS端
	 */
	private String loginSource;

	public EnterpriseLoginRequest() {
		super();
	}

	public EnterpriseLoginRequest(String customerNo, String loginName,
			String password, String loginSource) {
		super();
		this.customerNo = customerNo;
		this.loginName = loginName;
		this.password = password;
		this.loginSource = loginSource;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}
	
}
