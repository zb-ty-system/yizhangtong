/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseBankNoQueryResponse <br/>
 * Function: 查询已绑定银行账号响应. <br/>
 * Date: 2016年12月12日 下午3:37:20 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseBankNoQueryResponse extends BaseResponse{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** 银行账号  */
	private String bankAccountNo;
	
	/**
	 * existFlg:是否存在 true：存在，false ：不存在
	 */
	private boolean existFlg;
	
	public EnterpriseBankNoQueryResponse() {
		super();
	}
	public EnterpriseBankNoQueryResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public EnterpriseBankNoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public boolean isExistFlg() {
		return existFlg;
	}
	public void setExistFlg(boolean existFlg) {
		this.existFlg = existFlg;
	}
	
}
