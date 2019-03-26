package com.zb.sms.common.constants;

/**
 * 功能: 是/否
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/8 0008 14:06
 * 版本: V1.0
 */
public enum YesNo {

    YES(1, "是"), NO(0, "否");

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
    YesNo(int value, String desc) {
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
