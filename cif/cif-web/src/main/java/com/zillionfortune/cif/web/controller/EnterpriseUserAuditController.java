/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.EnterpriseUserAuditServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditResponse;

/**
 * ClassName: EnterpriseUserAuditController <br/>
 * Function: 企业会员认证信息审核接口http入口. <br/>
 * Date: 2016年12月28日 下午2:05:06 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/enterpriseuserauditservice")
public class EnterpriseUserAuditController {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnterpriseUserAuditServiceFacade enterpriseUserAuditServiceFacade;
	
	/**
	 * audit:企业会员认证信息审核. <br/>
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/audit", method=RequestMethod.POST)
	public EnterpriseUserAuditResponse audit(EnterpriseUserAuditRequest req) {
		return enterpriseUserAuditServiceFacade.audit(req);
	}
	
}
