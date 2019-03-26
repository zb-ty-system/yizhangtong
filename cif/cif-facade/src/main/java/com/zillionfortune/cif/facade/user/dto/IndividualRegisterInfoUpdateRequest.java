package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class IndividualRegisterInfoUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 * 必输
	 */
	private String memberId;
	
	/** 手机号  */
	private String mobile;
	
	/** 昵称 */
	private String nickName;
	
	/** 头像地址 */
	private String headImgUrl;
	
	/** 邮箱 */
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}
