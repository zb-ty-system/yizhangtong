/*
 * Copyright (c) 2017, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

/**
 * ClassName: OperatorInfoQueryDto <br/>
 * Function: 查询操作员信息. <br/>
 * Date: 2017年1月16日 上午11:32:11 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class OperatorInfoQueryDto {
	private String memberId;
	private Long operatorId;
	private String mobile;
	private String userName;
	private String email;
	private Integer status;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
