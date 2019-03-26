/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseQualificationUpdateDto <br/>
 * Function: 企业资质更新Request. <br/>
 * Date: 2016年12月20日 上午11:34:07 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseQualificationUpdateRequest extends BaseRequest {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员ID
	 * 必输
	 */
	private String memberId;
	
	/**
	 * businessLicenceUrl:营业执照下载地址
	 */
	private String businessLicenceUrl;

    /**
     * organizationCodeCertificateUrl:组织机构代码证下载地址
     */
    private String organizationCodeCertificateUrl;

    /**
     * taxRegistrationCertificateUrl:税务登记证（国税）下载地址
     */
    private String taxRegistrationCertificateUrl;

    /**
     * taxRegistrationCertificateLocalUrl:税务登记证（地税）下载地址
     */
    private String taxRegistrationCertificateLocalUrl;

    /**
     * legalPersonCertificateFrontUrl:法人代表身份证正面下载地址
     */
    private String legalPersonCertificateFrontUrl;

    /**
     * legalPersonCertificateBackUrl:法人代表身份证反面下载地址
     */
    private String legalPersonCertificateBackUrl;

    /**
     * accountOpeningLicenseUrl:开户许可证下载地址
     */
    private String accountOpeningLicenseUrl;

    /**
     * powerOfAttorneyUrl:法定代表人／负责人授权委托书下载地址
     */
    private String powerOfAttorneyUrl;

    /**
     * serviceApplicationUrl:企业服务申请书下载地址
     */
    private String serviceApplicationUrl;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
