/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.integration.payment.dto;

import java.util.List;

/**
 * ClassName: MsgSignSendResult <br/>
 * Function: 个人会员查询已绑定银行卡请求结果. <br/>
 * Date: 2016年12月15日 上午9:28:16 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@SuppressWarnings("rawtypes")
public class BindCardQueryResult extends BaseResult{

	private List bankCardList;

	public List getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List bankCardList) {
		this.bankCardList = bankCardList;
	}
	
}
