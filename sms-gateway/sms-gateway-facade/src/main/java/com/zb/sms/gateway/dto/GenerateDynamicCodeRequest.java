package com.zb.sms.gateway.dto;

/**
 * 功能: 生成随机码请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 15:14
 * 版本: V1.0
 */
public class GenerateDynamicCodeRequest extends BaseRpcRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -4463200307828103575L;

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 短信编码
     */
    private String smsCode;

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

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
