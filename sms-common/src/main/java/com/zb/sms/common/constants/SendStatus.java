package com.zb.sms.common.constants;

/**
 * 功能: 短信发送状态
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 13:04
 * 版本: V1.0
 */
public enum SendStatus {

    SEND_ING(0, "发送中"), SEND_SUCCESS(1, "发送成功"), SEND_FAIL(2, "发送失败");

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
    SendStatus(int value, String desc) {
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
