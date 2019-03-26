package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class EnterpriseRegisterInfoUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 * 必输
	 */
	private String memberId;
	
	/**
	 * 操作员ID 操作员表主键
	 * 必输
	 */
	private String operatorId;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 头像地址
	 */
	private String headImgUrl;
	
	/**
	 * 邮箱
	 */
	private String email;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}