/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: UserAuthServiceRequest <br/>
 * Function: 个人会员实名认证响应对象. <br/>
 * Date: 2016年12月2日 上午10:49:09 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class UserAuthServiceResponse extends BaseResponse{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	
	public UserAuthServiceResponse(String respCode, String resultMsg) {
		super();
		this.respCode = respCode;
		this.resultMsg = resultMsg;
	}

	public UserAuthServiceResponse(String respCode, String resultCode,String resultMsg) {
		super();
		this.respCode = respCode;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
