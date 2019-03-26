package com.zillionfortune.cif.dal.entity;

import java.util.Date;

public class EnterpriseQualification {
    private Long id;

    private String customerId;

    private String businessLicenceUrl;

    private String organizationCodeCertificateUrl;

    private String taxRegistrationCertificateUrl;

    private String taxRegistrationCertificateLocalUrl;

    private String legalPersonCertificateFrontUrl;

    private String legalPersonCertificateBackUrl;

    private String accountOpeningLicenseUrl;

    private String powerOfAttorneyUrl;

    private String serviceApplicationUrl;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBusinessLicenceUrl() {
        return businessLicenceUrl;
    }

    public void setBusinessLicenceUrl(String businessLicenceUrl) {
        this.businessLicenceUrl = businessLicenceUrl == null ? null : businessLicenceUrl.trim();
    }

    public String getOrganizationCodeCertificateUrl() {
        return organizationCodeCertificateUrl;
    }

    public void setOrganizationCodeCertificateUrl(String organizationCodeCertificateUrl) {
        this.organizationCodeCertificateUrl = organizationCodeCertificateUrl == null ? null : organizationCodeCertificateUrl.trim();
    }

    public String getTaxRegistrationCertificateUrl() {
        return taxRegistrationCertificateUrl;
    }

    public void setTaxRegistrationCertificateUrl(String taxRegistrationCertificateUrl) {
        this.taxRegistrationCertificateUrl = taxRegistrationCertificateUrl == null ? null : taxRegistrationCertificateUrl.trim();
    }

    public String getTaxRegistrationCertificateLocalUrl() {
        return taxRegistrationCertificateLocalUrl;
    }

    public void setTaxRegistrationCertificateLocalUrl(String taxRegistrationCertificateLocalUrl) {
        this.taxRegistrationCertificateLocalUrl = taxRegistrationCertificateLocalUrl == null ? null : taxRegistrationCertificateLocalUrl.trim();
    }

    public String getLegalPersonCertificateFrontUrl() {
        return legalPersonCertificateFrontUrl;
    }

    public void setLegalPersonCertificateFrontUrl(String legalPersonCertificateFrontUrl) {
        this.legalPersonCertificateFrontUrl = legalPersonCertificateFrontUrl == null ? null : legalPersonCertificateFrontUrl.trim();
    }

    public String getLegalPersonCertificateBackUrl() {
        return legalPersonCertificateBackUrl;
    }

    public void setLegalPersonCertificateBackUrl(String legalPersonCertificateBackUrl) {
        this.legalPersonCertificateBackUrl = legalPersonCertificateBackUrl == null ? null : legalPersonCertificateBackUrl.trim();
    }

    public String getAccountOpeningLicenseUrl() {
        return accountOpeningLicenseUrl;
    }

    public void setAccountOpeningLicenseUrl(String accountOpeningLicenseUrl) {
        this.accountOpeningLicenseUrl = accountOpeningLicenseUrl == null ? null : accountOpeningLicenseUrl.trim();
    }

    public String getPowerOfAttorneyUrl() {
        return powerOfAttorneyUrl;
    }

    public void setPowerOfAttorneyUrl(String powerOfAttorneyUrl) {
        this.powerOfAttorneyUrl = powerOfAttorneyUrl == null ? null : powerOfAttorneyUrl.trim();
    }

    public String getServiceApplicationUrl() {
        return serviceApplicationUrl;
    }

    public void setServiceApplicationUrl(String serviceApplicationUrl) {
        this.serviceApplicationUrl = serviceApplicationUrl == null ? null : serviceApplicationUrl.trim();
    }

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}