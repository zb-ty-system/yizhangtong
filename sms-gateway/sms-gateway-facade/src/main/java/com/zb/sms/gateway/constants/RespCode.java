package com.zb.sms.gateway.constants;

/**
 * 功能: RPC通讯响应码
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 09:37
 * 版本: V1.0
 */
public enum RespCode {

    SUCCESS(0000, "响应成功"), FAIL(9999, "响应失败");

    /**
     * 键值
     */
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 构造函数
     *
     * @param value 键值
     * @param desc  描述
     */
    RespCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
