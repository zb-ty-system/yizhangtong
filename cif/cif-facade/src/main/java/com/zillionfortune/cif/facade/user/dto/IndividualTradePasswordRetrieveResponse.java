/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: IndividualTradePasswordRetrieveResponse <br/>
 * Function: 个人会员重置交易密码反馈对象. <br/>
 * Date: 2016年12月6日 下午1:53:44 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class IndividualTradePasswordRetrieveResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/** 会员编号  必输 */
	private String memberId;

	public IndividualTradePasswordRetrieveResponse() {
		super();
	}

	public IndividualTradePasswordRetrieveResponse(String respCode,String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public IndividualTradePasswordRetrieveResponse(String respCode,String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualTradePasswordRetrieveResponse(String respCode) {
		super(respCode);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
