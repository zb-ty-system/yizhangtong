package com.zillionfortune.util.common.dto;

import com.zillionfortune.util.common.enmu.ResultCodeEnum;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String respCode = "000000";//响应状态编码

    protected String resultCode = ResultCodeEnum.SUCCESS.getCode();//业务处理结果编码

    protected String resultMsg = ResultCodeEnum.SUCCESS.getMsg();//业务处理结果描述

    public BaseResponse() {
    }

    public BaseResponse(String respCode, String resultMsg) {
        this.respCode = respCode;
        this.resultMsg = resultMsg;
    }

    public BaseResponse(String respCode, String resultCode, String resultMsg) {
        this.respCode = respCode;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public static BaseResponse build() {
        return new BaseResponse();
    }

    public static BaseResponse build(String respCode, String resultMsg) {
        return new BaseResponse(respCode, resultMsg);
    }

    public static BaseResponse build(String respCode, String resultCode, String resultMsg) {
        return new BaseResponse(respCode, resultCode, resultMsg);
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
