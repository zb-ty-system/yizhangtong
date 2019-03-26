package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseTradePasswordVerifyResponse <br/>
 * Function: 企业会员验证交易密码反馈对象. <br/>
 * Date: 2016年12月6日 下午1:53:44 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseTradePasswordVerifyResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/** 会员编号  必输 */
	private String memberId;

	public EnterpriseTradePasswordVerifyResponse() {
		super();
	}

	public EnterpriseTradePasswordVerifyResponse(String respCode,String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseTradePasswordVerifyResponse(String respCode,String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseTradePasswordVerifyResponse(String respCode) {
		super(respCode);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
