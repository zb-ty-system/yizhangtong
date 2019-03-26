package com.zillionfortune.cif.service.redis.model;

/**
 * ClassName: EnterpriseModel <br/>
 * Function: 保存在redis中的企业用户信息. <br/>
 * Date: 2016年11月22日 下午12:53:58 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseRedisModel {
	
	/**
	 * 会员Id
	 */
	private String memberId;
	
	/**
	 * 操作员ID
	 */
	private String operatorId;
	
	/**
	 * 企业名称
	 */
	private String enterpriseName;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
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
	 * 登入状态
	 * 1未登入 2已登入
	 */
	private String loginStatus = "1";
	
	/**
	 * 登入时间
	 */
	private String loginTime;

	
	public EnterpriseRedisModel() {
		super();
	}

	public EnterpriseRedisModel(String memberId, String operatorId,
			String enterpriseName, String mobile, String userName,
			String email, Integer loginSource, String loginStatus,
			String loginTime) {
		super();
		this.memberId = memberId;
		this.operatorId = operatorId;
		this.enterpriseName = enterpriseName;
		this.mobile = mobile;
		this.userName = userName;
		this.email = email;
		this.loginSource = loginSource;
		this.loginStatus = loginStatus;
		this.loginTime = loginTime;
	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
}
