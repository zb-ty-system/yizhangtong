package com.zb.sms.common.constants;

/**
 * 功能: 服务状态
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:45
 * 版本: V1.0
 */
public enum ServiceStatus {

    USING(1, "启用"), UNUSED(0, "停用");

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
    ServiceStatus(int value, String desc) {
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
