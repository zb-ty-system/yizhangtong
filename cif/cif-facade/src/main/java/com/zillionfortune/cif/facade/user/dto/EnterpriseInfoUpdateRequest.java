package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class EnterpriseInfoUpdateRequest extends BaseRequest { 

	private static final long serialVersionUID = 1L;

	/**
	 * memberId
	 * 必输
	 */
	private String memberId; 
	
	/** 企业证件有效期 必输 */
	private String certExpDate;
	
	/** 法人姓名 */
	private String legalPersonName;
	
	/** 法人证件类型 */
	private Integer legalPersonCertificateType;
	
	/** 法人证件号码  */
	private String legalPersonCertificateNo;
	
	/** 法人证件有效期 必输 */
	private String legalPersonCertExpDate;
	
	/** 企业名称 */
	private String enterpriseName;
	
	/** 企业注册地址 */
	private String registerAddress;

	/** 法人代表联系手机  */
	private String phone;
	
	/**
	 * industry:企业所属行业
	 */
	private Integer industry;
	
	/**
	 * enterpriseType:企业类型
	 */
	private Integer enterpriseType;
	
	/** 企业邮政编码  */
	private String postCode;
	
	/** 公司证件类型 */
	private Integer certificateType;
	
	/** 公司证件号码  */
	private String certificateNo;
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public Integer getLegalPersonCertificateType() {
		return legalPersonCertificateType;
	}

	public void setLegalPersonCertificateType(Integer legalPersonCertificateType) {
		this.legalPersonCertificateType = legalPersonCertificateType;
	}

	public String getLegalPersonCertificateNo() {
		return legalPersonCertificateNo;
	}

	public void setLegalPersonCertificateNo(String legalPersonCertificateNo) {
		this.legalPersonCertificateNo = legalPersonCertificateNo;
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

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
