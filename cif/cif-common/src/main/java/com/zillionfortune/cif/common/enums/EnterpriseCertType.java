package com.zillionfortune.cif.common.enums;

/**
 * @desc 终端来源枚举
 * @author zhengrunlong
 *
 */
public enum EnterpriseCertType {
	
	BUSINESS_LICENSE(1,"普通营业执照（非三证合一）"),
	
	SOCIAL_CREDIT_CERTIFICATE(2,"社会信用代码证（三证合一）"),
	
	OTHER_CERTIFICATE(3,"其他");
	
	private int code;
	private String desc;
	
	private EnterpriseCertType(int code, String desc) {
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
    	for(EnterpriseCertType item : EnterpriseCertType.values()){
    		if(item.code == code){
    			return item.desc();
    		}
    	}
    	
    	return null;
    }
	 
	public static EnterpriseCertType getEnumItem(int code) {
		for (EnterpriseCertType item : EnterpriseCertType.values()) {
			if (item.code() == code) {
				return item;
			}
		}
		return null;
	}
	 
}
