package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseTradePasswordModifyResponse <br/>
 * Function: 企业会员更新交易密码反馈对象. <br/>
 * Date: 2016年12月6日 下午1:53:44 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseTradePasswordModifyResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/** 会员编号  必输 */
	private String memberId;

	public EnterpriseTradePasswordModifyResponse() {
		super();
	}

	public EnterpriseTradePasswordModifyResponse(String respCode,String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseTradePasswordModifyResponse(String respCode,String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseTradePasswordModifyResponse(String respCode) {
		super(respCode);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
