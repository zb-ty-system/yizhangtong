package com.zillionfortune.cif.common.enums;

/**
 * @desc 终端来源枚举
 * @author zhengrunlong
 *
 */
public enum PersonCertType {
	
	IDENTITY_CARD(1,"居民身份证"),
	
	TEMP_IDENTITY_CARD(2,"临时居民身份证"),
	
	SOLDIER_IDENTITY_CARD(3,"军人身份证"),
	
	ARMED_POLICE_IDENTITY_CARD(4,"武装警察身份证 "),
	
	GANGAO_IDENTITY_CARD(5,"港澳居民往来内地通行证 "),
	
	TAIWAN_IDENTITY_CARD(6,"台湾居民来往大陆同行证 "),
	
	FOREIGN_PASSPORT(7,"外国护照 "); 
	
	private int code;
	private String desc;
	
	private PersonCertType(int code, String desc) {
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
    	for(PersonCertType item : PersonCertType.values()){
    		if(item.code == code){
    			return item.desc();
    		}
    	}
    	
    	return null;
    }
	 
	public static PersonCertType getEnumItem(int code) {
		for (PersonCertType item : PersonCertType.values()) {
			if (item.code() == code) {
				return item;
			}
		}
		return null;
	}
	 
}
