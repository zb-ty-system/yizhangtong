package com.zillionfortune.cif.service.redis.model;

import java.io.Serializable;

/**
 * 
 * ClassName: IndividualRedisModel <br/>
 * Function: 保存在redis中的个人用户信息. <br/>
 * Date: 2016年11月23日 上午9:43:24 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class IndividualRedisModel implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员Id
	 */
	private String memberId;
	
	/**
	 * 联系手机号
	 */
	private String phoneNo;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 登入来源
	 *  1：PC端；2：Android端；3：IOS端 
	 */
	private Integer loginSource;
	
	/**
	 * 是否登入
	 * 1未登入 2已登入
	 */
	private String loginStatus = "1";
	
	/**
	 * 登入时间
	 */
	private String loginTime;
	

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(Integer loginSource) {
		this.loginSource = loginSource;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}


}
