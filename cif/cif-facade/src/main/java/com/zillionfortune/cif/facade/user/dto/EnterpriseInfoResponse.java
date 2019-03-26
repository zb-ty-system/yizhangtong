package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseExtInfoQueryResponse <br/>
 * Function: 企业会员扩展信息. <br/>
 * Date: 2016年11月21日 上午10:07:46 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class EnterpriseInfoResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6138634634469992569L;

	private Integer certificateType;
	
	private String certificateNo;

	public EnterpriseInfoResponse() {
		super();
	}

	public EnterpriseInfoResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseInfoResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

}
