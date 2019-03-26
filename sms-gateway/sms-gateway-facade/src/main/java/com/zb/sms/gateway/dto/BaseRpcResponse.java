package com.zb.sms.gateway.dto;

import java.io.Serializable;

/**
 * 功能: 远程传输响应对象基类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/9 0009 15:49
 * 版本: V1.0
 */
public class BaseRpcResponse implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -6975943379662084984L;

    /**
     * 通讯状态码
     */
    private int respCode;

    /**
     * 业务响应码
     */
    private int resultCode;

    /**
     * 业务响应描述
     */
    private String resultDesc;

    /**
     * 默认构造函数
     */
    public BaseRpcResponse() {
    }

    /**
     * 构造函数
     *
     * @param respCode
     * @param resultCode
     * @param resultDesc
     */
    public BaseRpcResponse(int respCode, int resultCode, String resultDesc) {
        this.respCode = respCode;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
