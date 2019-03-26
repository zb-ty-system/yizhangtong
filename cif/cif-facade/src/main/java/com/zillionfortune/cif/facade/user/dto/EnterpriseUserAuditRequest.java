/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseUserAuditRequest <br/>
 * Function: 企业会员认证信息审核接口_请求. <br/>
 * Date: 2016年12月27日 下午5:51:04 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseUserAuditRequest extends BaseRequest {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	/**
	 * enterpriseAuditStatus:企业审核状态			0：待审核；1：审核通过；2：审核不通过； 
	 */
	private Integer enterpriseAuditStatus;
	/**
	 * legalPersonAuditStatus:法人审核状态			0：待审核；1：审核通过；2：审核不通过； 
	 */
	private Integer legalPersonAuditStatus;
	
	/**
	 * enterpriseAuditComment:企业审核意见
	 */
	private String enterpriseAuditComment;
	/**
	 * legalPersonAuditComment:法人审核意见
	 */
	private String legalPersonAuditComment;

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
	public String getEnterpriseAuditComment() {
		return enterpriseAuditComment;
	}
	public void setEnterpriseAuditComment(String enterpriseAuditComment) {
		this.enterpriseAuditComment = enterpriseAuditComment;
	}
	public String getLegalPersonAuditComment() {
		return legalPersonAuditComment;
	}
	public void setLegalPersonAuditComment(String legalPersonAuditComment) {
		this.legalPersonAuditComment = legalPersonAuditComment;
	}
}
