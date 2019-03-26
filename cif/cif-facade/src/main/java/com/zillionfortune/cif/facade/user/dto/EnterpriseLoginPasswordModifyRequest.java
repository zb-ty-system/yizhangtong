/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseLoginPasswordModifyRequest <br/>
 * Function: 企业会员更新登录密码业务Request. <br/>
 * Date: 2016年12月14日 下午2:01:17 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginPasswordModifyRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	
	/**
	 * operatorId:操作员Id.
	 */
	private String operatorId;
	
	/**
	 * newPassword:新密码.
	 */
	private String newPassword;
	
	/**
	 * orgiPassword:原密码.
	 */
	private String orgiPassword;

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
	 * 获取operatorId的值.
	 *
	 * @return operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置operatorId的值.
	 *
	 * @param  operatorId
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	/**
	 * 获取orgiPassword的值.
	 *
	 * @return orgiPassword
	 */
	public String getOrgiPassword() {
		return orgiPassword;
	}

	/**
	 * 设置orgiPassword的值.
	 *
	 * @param  orgiPassword
	 */
	public void setOrgiPassword(String orgiPassword) {
		this.orgiPassword = orgiPassword;
	}

}
