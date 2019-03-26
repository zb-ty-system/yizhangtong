package com.zb.sms.gateway.dto;

/**
 * 功能: 获取短信批量发送单次最大手机号数量限制响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 14:25
 * 版本: V1.0
 */
public class GetMobileMaxLimitResponse extends BaseRpcResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -8621106284508730514L;

    /**
     * 短信批量发送单次最大手机号数量限制
     */
    private Integer mobileMaxLimit;

    /**
     * 构造函数
     *
     * @param respCode
     * @param resultCode
     * @param resultDesc
     */
    public GetMobileMaxLimitResponse(int respCode, int resultCode, String resultDesc) {
        super(respCode, resultCode, resultDesc);
    }

    public Integer getMobileMaxLimit() {
        return mobileMaxLimit;
    }

    public void setMobileMaxLimit(Integer mobileMaxLimit) {
        this.mobileMaxLimit = mobileMaxLimit;
    }
}
