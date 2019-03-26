package com.zillionfortune.cif.common.enums;


/**
 * 开关标识
 * 
 * @author litaiping
 * @version  ErrorCodeEnum.java, v 0.1 2016-7-27 上午9:50:25
 */
public enum OpenFlag {

    OPEN(1, "开启"), CLOSE(2, "关闭");

    private int value;
    private String desc;



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 通过code获取enum对象
     * 
     * @param value
     * @return ProductEnum
     */
    public static OpenFlag getEnum(int value) {
        for (OpenFlag item : values()) {
            if (item.getValue()== value) {
                return item;
            }
        }
        return null;
    }

    private OpenFlag(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
