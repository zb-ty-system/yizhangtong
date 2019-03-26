package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * @desc 个人基础信息响应对象
 * @author pengting
 * @date 2016年11月11日 下午2:17:51
 * @version 1.0.0
 */
public class IndividualBasicInfoQueryResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2603316529872750854L;
	private String memberId;
	private String mobile;
	private String userName;
	private String email;

	public IndividualBasicInfoQueryResponse() {
		super();
	}

	public IndividualBasicInfoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualBasicInfoQueryResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
