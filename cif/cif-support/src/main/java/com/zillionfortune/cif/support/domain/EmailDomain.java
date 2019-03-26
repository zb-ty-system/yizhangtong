package com.zillionfortune.cif.support.domain;

import java.io.Serializable;

public class EmailDomain implements Serializable {
    private String mailSubject     = "我最近在钱宝网上做任务、签到赚了点钱。一起来赚点外快吧！";

    private String mailHeader      = "我最近在钱宝网上做任务、签到赚了点钱。一起来赚点外快吧！";

    private String mailHeaderValue = "我最近在钱宝网上做任务、签到赚了点钱。一起来赚点外快吧！";

    private String personalName;

    private String emailBody;

    private String fromEmail       = "kefu@qbao.com";

    private String toEmail;

    private String realName;

    private int    toEmailCount;

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getMailHeader() {
        return mailHeader;
    }

    public void setMailHeader(String mailHeader) {
        this.mailHeader = mailHeader;
    }

    public String getMailHeaderValue() {
        return mailHeaderValue;
    }

    public void setMailHeaderValue(String mailHeaderValue) {
        this.mailHeaderValue = mailHeaderValue;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getToEmailCount() {
        return toEmailCount;
    }

    public void setToEmailCount(int toEmailCount) {
        this.toEmailCount = toEmailCount;
    }
}
