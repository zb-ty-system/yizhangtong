/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BasePageRequest;

/**
 * ClassName: EntUserQueryInfoByPageQueryRequest <br/>
 * Function: 企业会员信息列表分页查询—请求. <br/>
 * Date: 2016年12月28日 上午9:44:18 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EntUserQueryInfoByPageQueryRequest extends BasePageRequest{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	
	private String mobile;  //	注册手机号			
	private String customerNo;  //	商户号			
	private String enterpriseName;  //	企业名称			
	private Integer enterpriseAuditStatus;  //	企业审核状态			0：待审核；1：审核通过；2：审核不通过； 
	private Integer legalPersonAuditStatus;  //	法人审核状态			0：待审核；1：审核通过；2：审核不通过； 
	private Integer authStatus;  //	认证状态			0：未认证；1：已认证
	private String registerTimeStart;  //	注册时间区间			大于或等于该时间
	private String registerTimeEnd;  //	注册时间区间			小于该时间
	private String legalPersonName;// 	法人姓名
	
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
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getRegisterTimeStart() {
		return registerTimeStart;
	}
	public void setRegisterTimeStart(String registerTimeStart) {
		this.registerTimeStart = registerTimeStart;
	}
	public String getRegisterTimeEnd() {
		return registerTimeEnd;
	}
	public void setRegisterTimeEnd(String registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
