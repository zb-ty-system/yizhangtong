package com.zb.sms.gateway.dto;

/**
 * 功能: 获取短信批量发送单次最大手机号数量限制请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 14:23
 * 版本: V1.0
 */
public class GetMobileMaxLimitRequest extends BaseRpcRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 8134900009822419297L;

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 短信编码
     */
    private String smsCode;

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
}
