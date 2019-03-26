/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: BindCardRequest <br/>
 * Function: 企业会员查询绑定银行卡响应对象. <br/>
 * Date: 2016年12月12日 下午3:37:20 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseBindCardQueryResponse extends BaseResponse{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** memberId  */
	private String memberId;
	// 开户支行
    private String branchBankName; 
    // 开户行所在地
    private String bankAccountRegion;
    // 账户名称
    private String bankAccountName;
    // 银行账号
    private String bankAccountNo;
	
	public EnterpriseBindCardQueryResponse() {
		super();
	}
	public EnterpriseBindCardQueryResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public EnterpriseBindCardQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBranchBankName() {
		return branchBankName;
	}
	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}
	public String getBankAccountRegion() {
		return bankAccountRegion;
	}
	public void setBankAccountRegion(String bankAccountRegion) {
		this.bankAccountRegion = bankAccountRegion;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	
}
