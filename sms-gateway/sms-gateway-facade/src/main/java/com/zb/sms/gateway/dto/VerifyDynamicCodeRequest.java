package com.zb.sms.gateway.dto;

/**
 * 功能: 验证动态码请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 16:17
 * 版本: V1.0
 */
public class VerifyDynamicCodeRequest extends BaseRpcRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -7083956986354257655L;

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 流水号,保证唯一
     */
    private String sequence;

    /**
     * 随机码
     */
    private String dynamicCode;

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

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
