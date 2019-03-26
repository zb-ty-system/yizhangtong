package com.zillionfortune.cif.dal.entity;

import java.util.Date;

public class EnterpriseLoginLog {
    private Long id;

    private String loginName;

    private String customerNo;

    private Date loginTime;
    
    public EnterpriseLoginLog() {
		super();
	}

	public EnterpriseLoginLog(String loginName, String customerNo,
			Date loginTime) {
		super();
		this.loginName = loginName;
		this.customerNo = customerNo;
		this.loginTime = loginTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}