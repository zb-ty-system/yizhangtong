package com.zillionfortune.util.constant.product;

/**
 * Created by niuzhanjun on 2017/1/11 0011.
 */
public enum PatternCodeTypeEnum {
    CASH_MANAGEMENT("01", "现金管理"),
    PERIODIC_REGULAR("02", "定期");

    private String code;
    private String desc;

    PatternCodeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
