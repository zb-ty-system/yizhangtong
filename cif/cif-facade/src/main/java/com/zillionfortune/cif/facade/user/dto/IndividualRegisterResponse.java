package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class IndividualRegisterResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员编号
	 * 必输
	 */
	private String memberId;

	public IndividualRegisterResponse() {
		super();
	}

	public IndividualRegisterResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualRegisterResponse(String respCode,
			String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
		
	}

	public IndividualRegisterResponse( String respCode) {
		super(respCode);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
