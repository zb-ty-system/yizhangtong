package com.zb.sms.gateway.dto;

/**
 * 功能: 发送验证码短信响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/9 0009 15:46
 * 版本: V1.0
 */
public class SendDynamicCodeResponse extends BaseRpcResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2216488527000336499L;

    /**
     * 随机码
     */
    private String dynamicCode;

    /**
     * 构造函数
     *
     * @param respCode
     * @param resultCode
     * @param resultDesc
     */
    public SendDynamicCodeResponse(int respCode, int resultCode, String resultDesc) {
        super(respCode, resultCode, resultDesc);
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
