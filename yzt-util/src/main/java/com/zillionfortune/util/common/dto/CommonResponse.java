package com.zillionfortune.util.common.dto;

public class CommonResponse<T extends BaseDTO> extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResponse() {
    }

    public CommonResponse(String respCode, String resultMsg) {
        this.respCode = respCode;
        this.resultMsg = resultMsg;
    }

    public CommonResponse(String respCode, String resultCode, String resultMsg) {
        this.respCode = respCode;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public static CommonResponse build() {
        return new CommonResponse();
    }

    public static CommonResponse build(String respCode, String resultMsg) {
        return new CommonResponse(respCode, resultMsg);
    }

    public static CommonResponse build(String respCode, String resultCode, String resultMsg) {
        return new CommonResponse(respCode, resultCode, resultMsg);
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
