package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class IndividualInfoUpdateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/** 客户编号 必输  */
	private String customerId;
	
	/** 会员编号 必输  */
	private String memberId;
	
	public IndividualInfoUpdateResponse() {
		super();
	}

	public IndividualInfoUpdateResponse(String respCode) {
		super(respCode);
	}
	
	
	public IndividualInfoUpdateResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualInfoUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
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

}
