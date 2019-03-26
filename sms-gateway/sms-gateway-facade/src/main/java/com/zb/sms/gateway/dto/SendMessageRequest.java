package com.zb.sms.gateway.dto;

/**
 * 功能: 发送短信请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/9 0009 15:45
 * 版本: V1.0
 */
public class SendMessageRequest extends BaseRpcRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -3367616870346005120L;

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
}
