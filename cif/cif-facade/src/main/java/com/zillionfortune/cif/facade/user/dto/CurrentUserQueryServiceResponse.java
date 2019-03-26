package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: CurrentUserQueryServiceResponse <br/>
 * Function: 会员登录信息查询_响应对象. <br/>
 * Date: 2016年11月15日 下午4:50:54 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
public class CurrentUserQueryServiceResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String mobile;
	private String userName;
	private String email;
	
	public CurrentUserQueryServiceResponse() {
		super();
	}

	public CurrentUserQueryServiceResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public CurrentUserQueryServiceResponse(String respCode,String resultMsg) {
		super(respCode,resultMsg);
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
