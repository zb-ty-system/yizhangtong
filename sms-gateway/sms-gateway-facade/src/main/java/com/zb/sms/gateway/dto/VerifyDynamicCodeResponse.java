package com.zb.sms.gateway.dto;

/**
 * 功能: 验证动态码响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 16:18
 * 版本: V1.0
 */
public class VerifyDynamicCodeResponse extends BaseRpcResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -7281404319609890846L;

    /**
     * 是否通过验证
     */
    private boolean isVerifyed;

    /**
     * 构造函数
     *
     * @param respCode
     * @param resultCode
     * @param resultDesc
     */
    public VerifyDynamicCodeResponse(int respCode, int resultCode, String resultDesc) {
        super(respCode, resultCode, resultDesc);
    }

    public boolean isVerifyed() {
        return isVerifyed;
    }

    public void setIsVerifyed(boolean isVerifyed) {
        this.isVerifyed = isVerifyed;
    }
}
