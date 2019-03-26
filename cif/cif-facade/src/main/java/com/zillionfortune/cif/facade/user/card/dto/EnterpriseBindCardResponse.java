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
 * Function: 企业会员绑定银行卡响应对象. <br/>
 * Date: 2016年12月12日 下午3:37:20 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseBindCardResponse extends BaseResponse{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** memberId  */
	private String memberId;
	
	public EnterpriseBindCardResponse() {
		super();
	}
	public EnterpriseBindCardResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public EnterpriseBindCardResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
