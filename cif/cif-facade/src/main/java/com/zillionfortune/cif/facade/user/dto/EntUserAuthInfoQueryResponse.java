package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EntUserAuthInfoQueryResponse <br/>
 * Function: 企业用户认证信息. <br/>
 * Date: 2016年11月28日 上午10:45:42 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EntUserAuthInfoQueryResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4085852526576397341L;
	private String memberId;
	private String customerId;
	/** 认证等级 */
	private Integer authGrade;
	private String enterpriseName;
	private Integer certificateType;
	private String certificateNo;
	
	/** 企业资质信息 */
	private String businessLicenceUrl;//	营业执照下载地址
	private String organizationCodeCertificateUrl;//	组织机构代码证下载地址
	private String taxRegistrationCertificateUrl;//	税务登记证（国税）下载地址
	private String taxRegistrationCertificateLocalUrl;//	税务登记证（地税）下载地址
	private String legalPersonCertificateFrontUrl;//	法人代表身份证正面下载地址
	private String legalPersonCertificateBackUrl;//	法人代表身份证反面下载地址
	private String accountOpeningLicenseUrl;//	开户许可证下载地址
	private String powerOfAttorneyUrl;//	法定代表人／负责人授权委托书下载地址
	private String serviceApplicationUrl;//	企业服务申请书下载地址


	public EntUserAuthInfoQueryResponse() {
		super();
	}

	public EntUserAuthInfoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public EntUserAuthInfoQueryResponse(String respCode, String resultCode,
			String resultMsg) {
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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public String getBusinessLicenceUrl() {
		return businessLicenceUrl;
	}

	public void setBusinessLicenceUrl(String businessLicenceUrl) {
		this.businessLicenceUrl = businessLicenceUrl;
	}

	public String getOrganizationCodeCertificateUrl() {
		return organizationCodeCertificateUrl;
	}

	public void setOrganizationCodeCertificateUrl(
			String organizationCodeCertificateUrl) {
		this.organizationCodeCertificateUrl = organizationCodeCertificateUrl;
	}

	public String getTaxRegistrationCertificateUrl() {
		return taxRegistrationCertificateUrl;
	}

	public void setTaxRegistrationCertificateUrl(
			String taxRegistrationCertificateUrl) {
		this.taxRegistrationCertificateUrl = taxRegistrationCertificateUrl;
	}

	public String getTaxRegistrationCertificateLocalUrl() {
		return taxRegistrationCertificateLocalUrl;
	}

	public void setTaxRegistrationCertificateLocalUrl(
			String taxRegistrationCertificateLocalUrl) {
		this.taxRegistrationCertificateLocalUrl = taxRegistrationCertificateLocalUrl;
	}

	public String getLegalPersonCertificateFrontUrl() {
		return legalPersonCertificateFrontUrl;
	}

	public void setLegalPersonCertificateFrontUrl(
			String legalPersonCertificateFrontUrl) {
		this.legalPersonCertificateFrontUrl = legalPersonCertificateFrontUrl;
	}

	public String getLegalPersonCertificateBackUrl() {
		return legalPersonCertificateBackUrl;
	}

	public void setLegalPersonCertificateBackUrl(
			String legalPersonCertificateBackUrl) {
		this.legalPersonCertificateBackUrl = legalPersonCertificateBackUrl;
	}

	public String getAccountOpeningLicenseUrl() {
		return accountOpeningLicenseUrl;
	}

	public void setAccountOpeningLicenseUrl(String accountOpeningLicenseUrl) {
		this.accountOpeningLicenseUrl = accountOpeningLicenseUrl;
	}

	public String getPowerOfAttorneyUrl() {
		return powerOfAttorneyUrl;
	}

	public void setPowerOfAttorneyUrl(String powerOfAttorneyUrl) {
		this.powerOfAttorneyUrl = powerOfAttorneyUrl;
	}

	public String getServiceApplicationUrl() {
		return serviceApplicationUrl;
	}

	public void setServiceApplicationUrl(String serviceApplicationUrl) {
		this.serviceApplicationUrl = serviceApplicationUrl;
	}

}