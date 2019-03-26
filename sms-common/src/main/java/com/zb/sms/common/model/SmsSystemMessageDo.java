package com.zb.sms.common.model;

/**
 * 功能: 内部系统消息数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:16
 * 版本: V1.0
 */
public class SmsSystemMessageDo extends BaseDo {

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 短信编码
     */
    private String smsCode;

    /**
     * 短信接收方手机号,多个号码使用逗号分隔
     */
    private String mobile;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信接收方手机号数量
     */
    private int mobileCount;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMobileCount() {
        return mobileCount;
    }

    public void setMobileCount(int mobileCount) {
        this.mobileCount = mobileCount;
    }
}
