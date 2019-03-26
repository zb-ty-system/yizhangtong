package com.zillionfortune.cif.common.enums;

/**
 * 会员状态枚举
 * 
 * @author wangzinan
 *
 */
public enum UserStatusCodeEnum {

	/** 1：正常 **/
    NORMAL(1, "正常"),
    /** 2：冻结  **/
    FROZEN(2, "冻结"),
    /** 3：注销  **/
    CANCEL(3, "注销");
	
	// 会员状态code
	private int code;
	// 会员状态code描述
	private String desc;

	UserStatusCodeEnum(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
	 * @param code
	 * @return
	 */
	public static UserStatusCodeEnum getEnum(int code) {
		for (UserStatusCodeEnum item : values()) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return null;
	}

}
