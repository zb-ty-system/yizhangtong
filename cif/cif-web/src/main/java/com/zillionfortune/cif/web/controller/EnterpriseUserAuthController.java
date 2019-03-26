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

import com.zillionfortune.cif.facade.user.EnterpriseUserAuthServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceResponse;

/**
 * ClassName: EnterpriseUserAuthController <br/>
 * Function: 企业会员认证Http接口. <br/>
 * Date: 2016年12月7日 上午11:24:27 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/enterpriseuserauthservice")
public class EnterpriseUserAuthController {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnterpriseUserAuthServiceFacade enterpriseUserAuthServiceFacade;
	
	/**
	 * realNameAuth:企业会员实名认证. <br/>
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/realnameauth", method=RequestMethod.POST)
	public EnterpriseUserAuthServiceResponse realNameAuth(EnterpriseUserAuthServiceRequest req) {
		return enterpriseUserAuthServiceFacade.realNameAuth(req);
	}
}
