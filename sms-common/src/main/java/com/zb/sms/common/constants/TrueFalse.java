package com.zb.sms.common.constants;

/**
 * 功能: 真/假
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/8 0008 14:06
 * 版本: V1.0
 */
public enum TrueFalse {

    TRUE(1, "真"), FALSE(0, "假");

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
    TrueFalse(int value, String desc) {
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
