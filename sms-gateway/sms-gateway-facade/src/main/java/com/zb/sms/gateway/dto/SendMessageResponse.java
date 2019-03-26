package com.zb.sms.gateway.dto;

/**
 * 功能: 发送短信响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/9 0009 15:46
 * 版本: V1.0
 */
public class SendMessageResponse extends BaseRpcResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -5873646611785564982L;

    /**
     * 构造函数
     *
     * @param respCode
     * @param resultCode
     * @param resultDesc
     */
    public SendMessageResponse(int respCode, int resultCode, String resultDesc) {
        super(respCode, resultCode, resultDesc);
    }
}
