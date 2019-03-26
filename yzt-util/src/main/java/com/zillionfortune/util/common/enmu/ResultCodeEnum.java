package com.zillionfortune.util.common.enmu;

/**
 * Created by niuzhanjun on 2017/2/27 0027.
 */
public enum ResultCodeEnum {
    SUCCESS("0000", "成功"),
    EXCEPTION("9999", "系统异常");

    String code;
    String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
