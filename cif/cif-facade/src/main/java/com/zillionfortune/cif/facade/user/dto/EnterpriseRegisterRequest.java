package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class EnterpriseRegisterRequest extends BaseRequest {

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
	
	/** 企业证件类型 必输 */
	private String certificateType;
	
	/** 企业证件号码 必输 */
	private String certificateNo;
	
	/** 企业证件有效期 必输 */
	private String certExpDate;
	
	/** 法人姓名 必输 */
	private String legalPersonName;
	
	/** 法人证件类型 必输 */
	private String legalPersonCertificateType;
	
	/** 法人证件号码  必输*/
	private String legalPersonCertificateNo;
	
	/** 法人证件有效期 必输 */
	private String legalPersonCertExpDate;
	
	/** 企业名称  必输 */
	private String enterpriseName;
	
	/** 企业经营地址  必输 */
	private String registerAddress;

	/** 企业邮政编码  */
	private String postCode;
	
	/** 法人代表联系手机  必输 */
	private String phone;

	public String getLegalPersonCertificateNo() {
		return legalPersonCertificateNo;
	}

	public void setLegalPersonCertificateNo(String legalPersonCertificateNo) {
		this.legalPersonCertificateNo = legalPersonCertificateNo;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getLegalPersonCertificateType() {
		return legalPersonCertificateType;
	}

	public void setLegalPersonCertificateType(String legalPersonCertificateType) {
		this.legalPersonCertificateType = legalPersonCertificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

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

	public String getCertExpDate() {
		return certExpDate;
	}

	public void setCertExpDate(String certExpDate) {
		this.certExpDate = certExpDate;
	}

	public String getLegalPersonCertExpDate() {
		return legalPersonCertExpDate;
	}

	public void setLegalPersonCertExpDate(String legalPersonCertExpDate) {
		this.legalPersonCertExpDate = legalPersonCertExpDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
