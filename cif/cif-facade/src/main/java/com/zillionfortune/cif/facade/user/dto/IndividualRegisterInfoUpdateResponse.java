package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class IndividualRegisterInfoUpdateResponse  extends BaseResponse {
	
	private static final long serialVersionUID = 1L;

	/** 会员编号 必输 */
	private String memberId;


	public IndividualRegisterInfoUpdateResponse() {
		super();
	}

	public IndividualRegisterInfoUpdateResponse(String respCode,String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualRegisterInfoUpdateResponse(String respCode) {
		super(respCode);
	}
	
	public IndividualRegisterInfoUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
