package com.zillionfortune.cif.dal.entity;

import java.util.Date;

public class PersonLoginLog {
    private Long id;

    private String loginName;

    private Date loginTime;
    
    public PersonLoginLog() {
		super();
	}

	public PersonLoginLog(String loginName, Date loginTime) {
		super();
		this.loginName = loginName;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}