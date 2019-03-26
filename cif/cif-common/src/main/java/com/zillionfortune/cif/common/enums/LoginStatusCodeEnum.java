package com.zillionfortune.cif.common.enums;

/**
 * ClassName: LoginStatusCodeEnum <br/>
 * Function: 会员登入状态. <br/>
 * Date: 2016年12月6日 下午4:45:22 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public enum LoginStatusCodeEnum {

	/** 1：未登入 **/
    loginNo("1", "未登入"),
    /** 2：已登入  **/
    loginYes("2", "已登入");
	
	/**
	 * 会员状态code
	 */
	private String code;
	
	/**
	 * 会员状态code描述
	 */
	private String desc;

	LoginStatusCodeEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
	public static LoginStatusCodeEnum getEnum(String code) {
		for (LoginStatusCodeEnum item : values()) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return null;
	}

}
