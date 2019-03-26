package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class EnterpriseRegisterResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户号
	 * 必输
	 */
	private String customerId;
	
	/**
	 * 会员号
	 * 必输
	 */
	private String memberId;
	
	/**
	 * 商户号
	 * 必输
	 */
	private String customerNo;
	
	/**
	 * 操作员Id
	 * 必输
	 */
	private Long operatorId;

	public EnterpriseRegisterResponse() {
		super();
	}

	public EnterpriseRegisterResponse(String respCode,String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseRegisterResponse(String respCode) {
		super(respCode);
	}

	public EnterpriseRegisterResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
}
