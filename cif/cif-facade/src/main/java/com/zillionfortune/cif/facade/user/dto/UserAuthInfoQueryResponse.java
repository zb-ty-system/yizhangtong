package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * @desc 用户认证信息
 * @author pengting
 * @date 2016年11月11日 下午2:19:23
 * @version 1.0.0
 */
public class UserAuthInfoQueryResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4085852526576397341L;
	private String memberId;
	private String customerId;
	/** 认证等级 */
	private Integer authGrade;
	private String realName;
	private Integer certificateType;
	private String certificateNo;

	public UserAuthInfoQueryResponse() {
		super();
	}

	public UserAuthInfoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public UserAuthInfoQueryResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getAuthGrade() {
		return authGrade;
	}

	public void setAuthGrade(Integer authGrade) {
		this.authGrade = authGrade;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

}