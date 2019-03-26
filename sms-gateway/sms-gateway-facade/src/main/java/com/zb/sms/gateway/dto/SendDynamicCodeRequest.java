package com.zb.sms.gateway.dto;

/**
 * 功能: 发送验证码短信请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/9 0009 15:45
 * 版本: V1.0
 */
public class SendDynamicCodeRequest extends BaseRpcRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 5312785407083276735L;

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
     * 流水号,保证唯一
     */
    private String sequence;

    /**
     * 随机码长度,默认六位
     */
    private int codeLength = 6;

    /**
     * 是否包含英文字母
     */
    private boolean containsChar = false;

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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public boolean isContainsChar() {
        return containsChar;
    }

    public void setContainsChar(boolean containsChar) {
        this.containsChar = containsChar;
    }
}
