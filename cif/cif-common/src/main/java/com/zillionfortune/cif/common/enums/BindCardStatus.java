/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.common.enums;

/**
 * ClassName: BankCardType <br/>
 * Function: 卡绑定状态. <br/>
 * Date: 2016年12月12日 下午4:30:17 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public enum BindCardStatus {

	/** 绑定中  */
	BINDING(0,"绑定中"),
	/** 已绑定  */
	SUCCESS(1,"已绑定"),
	/** 已解绑  */
	UNBIND(2,"已解绑"),
	/** 绑定失败  */
	FAIL(3,"绑定失败");
	
	private int code;
	private String desc;

	BindCardStatus(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int code(){
        return code;
    }

    public String desc(){
        return desc;
    }
    
    public static BindCardStatus getEnumItem(int code) {
		for (BindCardStatus item : BindCardStatus.values()) {
			if (item.code() == code) {
				return item;
			}
		}
		return null;
	}
}
