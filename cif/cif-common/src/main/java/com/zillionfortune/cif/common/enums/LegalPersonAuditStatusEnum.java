/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.common.enums;

/**
 * ClassName: LegalPersonAuditStatusEnum <br/>
 * Function: 法人审核状态. <br/>
 * Date: 2016年12月27日 下午5:40:00 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public enum LegalPersonAuditStatusEnum {
	// 0：待审核；1：审核通过；2：审核不通过'
	CHECK_WAIT(0,"待审核"),
	CHECK_PASS(1,"审核通过"),
	CHECK_NOT_PASS(2,"审核不通过");
	
	private int code;
	private String desc;
	
	private LegalPersonAuditStatusEnum(int code, String desc) {
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
    	for(LegalPersonAuditStatusEnum item : LegalPersonAuditStatusEnum.values()){
    		if(item.code == code){
    			return item.desc();
    		}
    	}
    	
    	return null;
    }
	 
	public static LegalPersonAuditStatusEnum getEnumItem(int code) {
		for (LegalPersonAuditStatusEnum item : LegalPersonAuditStatusEnum.values()) {
			if (item.code() == code) {
				return item;
			}
		}
		return null;
	}
}
