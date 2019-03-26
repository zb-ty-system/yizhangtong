/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card.dto;

import java.util.List;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: BindCardRequest <br/>
 * Function: 个人会员查询已绑定银行卡响应对象. <br/>
 * Date: 2016年12月12日 下午3:37:20 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@SuppressWarnings("rawtypes")
public class BindCardQueryResponse extends BaseResponse{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** memberId  */
	private String memberId;
	/** 银行卡列表 */
	private List bankCardList;
	
	public BindCardQueryResponse() {
		super();
	}
	public BindCardQueryResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public BindCardQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public List getBankCardList() {
		return bankCardList;
	}
	public void setBankCardList(List bankCardList) {
		this.bankCardList = bankCardList;
	}
}
