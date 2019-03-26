package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseInfoQueryByCertTypeNoRequest <br/>
 * Function: 通过证件类型证件号码查询企业基本信息. <br/>
 * Date: 2016年11月16日 上午10:19:21 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class EnterpriseInfoQueryByCertTypeNoRequest extends BaseRequest {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1744362333610628183L;

	  /**
     * 企业证件类型
     */
	private Integer certificateType;
	
	/**
	 * 企业证件号码
	 */
	private String certificateNo;

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
