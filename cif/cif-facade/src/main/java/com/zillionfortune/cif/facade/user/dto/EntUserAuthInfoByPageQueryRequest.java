/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BasePageRequest;

/**
 * ClassName: EntUserAuthInfoByPageQueryRequest <br/>
 * Function: 企业认证信息列表查询—请求. <br/>
 * Date: 2016年12月28日 上午9:44:18 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EntUserAuthInfoByPageQueryRequest extends BasePageRequest{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	/**
	 * enterpriseAuditStatus:	企业审核状态
	 */
	private Integer  enterpriseAuditStatus;
	/**
	 * legalPersonAuditStatus:	法人审核状态
	 */
	private Integer legalPersonAuditStatus;
	private String mobile;  //	注册手机号			
	private String customerNo;  //	商户号			
	private String enterpriseName;  //	企业名称	
	private String legalPersonName;// 	法人姓名
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Integer getEnterpriseAuditStatus() {
		return enterpriseAuditStatus;
	}
	public void setEnterpriseAuditStatus(Integer enterpriseAuditStatus) {
		this.enterpriseAuditStatus = enterpriseAuditStatus;
	}
	public Integer getLegalPersonAuditStatus() {
		return legalPersonAuditStatus;
	}
	public void setLegalPersonAuditStatus(Integer legalPersonAuditStatus) {
		this.legalPersonAuditStatus = legalPersonAuditStatus;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
}
