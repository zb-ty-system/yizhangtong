package com.zillionfortune.cif.dal.entity;

import java.util.Date;

public class EnterpriseInfo {
    private Long id;

    private String customerId;

    private String enterpriseName;

	private Integer certificateType;
	
	private String certificateNo;

	private Date certificateExpireDate;
	
	private String phoneNo;
	 
    private String legalPersonName;

    private Integer legalPersonCertificateType;

    private String legalPersonCertificateNo;
    
    private Date legalPersonCertificateExpireDate;

    private String enterpriseRegisterAddr;
    
    private String postCode;
    
    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;
    // 开户支行
    private String branchBankName; 
    // 开户行所在地
    private String bankAccountRegion;
    // 账户名称
    private String bankAccountName;
    // 银行账号
    private String bankAccountNo;
    // 企业类型
    private Integer enterpriseType;
    // 所属行业
    private Integer industry;
    // 企业审核状态：0：待审核；1：审核通过；2：审核不通过  enterprise_audit_status
    private Integer enterpriseAuditStatus;
    // 法人审核状态：0：待审核；1：审核通过；2：审核不通过  legal_person_audit_status
    private Integer legalPersonAuditStatus;
    
    // 企业审核备注 enterprise_audit_comment
    private String enterpriseAuditComment;
    // 法人审核备注 legal_person_audit_comment
    private String legalPersonAuditComment;

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
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
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

	public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
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
        this.legalPersonCertificateNo = legalPersonCertificateNo == null ? null : legalPersonCertificateNo.trim();
    }

    public String getEnterpriseRegisterAddr() {
        return enterpriseRegisterAddr;
    }

    public void setEnterpriseRegisterAddr(String enterpriseRegisterAddr) {
        this.enterpriseRegisterAddr = enterpriseRegisterAddr == null ? null : enterpriseRegisterAddr.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

	public Date getCertificateExpireDate() {
		return certificateExpireDate;
	}

	public void setCertificateExpireDate(Date certificateExpireDate) {
		this.certificateExpireDate = certificateExpireDate;
	}

	public Date getLegalPersonCertificateExpireDate() {
		return legalPersonCertificateExpireDate;
	}

	public void setLegalPersonCertificateExpireDate(
			Date legalPersonCertificateExpireDate) {
		this.legalPersonCertificateExpireDate = legalPersonCertificateExpireDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public String getBankAccountRegion() {
		return bankAccountRegion;
	}

	public void setBankAccountRegion(String bankAccountRegion) {
		this.bankAccountRegion = bankAccountRegion;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getEnterpriseAuditStatus() {
		return enterpriseAuditStatus;
	}

	public void setEnterpriseAuditStatus(Integer enterpriseAuditStatus) {
		this.enterpriseAuditStatus = enterpriseAuditStatus;
	}

	public Integer getLegalPersonAuditStatus() {
		return legalPersonAuditStatus;
	}

	public void setLegalPersonAuditStatus(Integer legalPersonAuditStatus) {
		this.legalPersonAuditStatus = legalPersonAuditStatus;
	}

	public String getEnterpriseAuditComment() {
		return enterpriseAuditComment;
	}

	public void setEnterpriseAuditComment(String enterpriseAuditComment) {
		this.enterpriseAuditComment = enterpriseAuditComment;
	}

	public String getLegalPersonAuditComment() {
		return legalPersonAuditComment;
	}

	public void setLegalPersonAuditComment(String legalPersonAuditComment) {
		this.legalPersonAuditComment = legalPersonAuditComment;
	}
}