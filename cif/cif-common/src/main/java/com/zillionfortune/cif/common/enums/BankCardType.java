/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.common.enums;

/**
 * ClassName: BankCardType <br/>
 * Function: 银行卡类型. <br/>
 * Date: 2016年12月12日 下午4:30:17 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public enum BankCardType {

	DEBIT_CARD("D","借记卡"),
	CREDIT_CARD("C","信用卡");
	
	private String code;
	private String desc;

	BankCardType(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String code(){
        return code;
    }

    public String desc(){
        return desc;
    }
    
    public static BankCardType getEnumItem(String code) {
		for (BankCardType item : BankCardType.values()) {
			if (item.code().equals(code)) {
				return item;
			}
		}
		return null;
	}
}
