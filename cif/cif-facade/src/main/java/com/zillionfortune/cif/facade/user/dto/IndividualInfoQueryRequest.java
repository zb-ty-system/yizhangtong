package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: UserInfoQueryRequest <br/>
 * Function: 个人会员查询请求数据对象. <br/>
 * Date: 2016年11月16日 上午10:19:21 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class IndividualInfoQueryRequest extends BaseRequest {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1744362333610628183L;
	private String memberId;
	private String customerId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
