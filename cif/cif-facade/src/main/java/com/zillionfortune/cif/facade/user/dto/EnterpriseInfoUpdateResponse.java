package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class EnterpriseInfoUpdateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L; 
	
	/**
	 * memberId
	 * 必输
	 */
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public EnterpriseInfoUpdateResponse() {
		super();
	}

	public EnterpriseInfoUpdateResponse(String respCode) {
		super(respCode);
	}
	
	public EnterpriseInfoUpdateResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseInfoUpdateResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
}
