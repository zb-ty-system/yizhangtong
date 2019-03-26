package com.zillionfortune.cif.common.enums;

/**
 * @desc 终端来源枚举
 * @author zhengrunlong
 *
 */
public enum RegisterSource {
	
	PC(1,"pc端"),
	
	ANDROID(2,"Android客户端"),
	
	IOS(3,"IOS客户端");
	
	private int code;
	private String desc;
	
	private RegisterSource(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int code() {
		return code;
	}
	
	public String desc() {
		return desc;
	}
	
	 public static String getDesc(int code){
    	for(RegisterSource item : RegisterSource.values()){
    		if(item.code == code){
    			return item.desc();
    		}
    	}
    	
    	return null;
    }
	 
	public static RegisterSource getEnumItem(int code) {
		for (RegisterSource item : RegisterSource.values()) {
			if (item.code() == code) {
				return item;
			}
		}
		return null;
	}
	 
}
