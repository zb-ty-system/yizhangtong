package com.zb.sms.gateway.dto;

/**
 * 功能: 生成随机码响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 15:14
 * 版本: V1.0
 */
public class GenerateDynamicCodeResponse extends BaseRpcResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 8936982546112434874L;

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
    public GenerateDynamicCodeResponse(int respCode, int resultCode, String resultDesc) {
        super(respCode, resultCode, resultDesc);
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
