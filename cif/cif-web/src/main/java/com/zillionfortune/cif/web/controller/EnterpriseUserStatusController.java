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

import com.zillionfortune.cif.facade.user.EnterpriseUserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateResponse;

/**
 * ClassName: EnterpriseUserStatusController <br/>
 * Function: 企业会员冻结/解冻/注销   Http接口. <br/>
 * Date: 2016年12月6日 上午11:18:58 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/enterpriseuserstatusservice")
public class EnterpriseUserStatusController {

    protected final Logger                  LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EnterpriseUserStatusServiceFacade enterpriseUserStatusServiceFacade;
    
    /**
     * 个人会员冻结测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserstatusservice/frozen.html?memberId=100001100004
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/frozen", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseStatusUpdateResponse enterpriseFrozen(EnterpriseStatusUpdateRequest req) {
    	EnterpriseStatusUpdateResponse response = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
    	return response;
    }
    
    /**
     * 个人会员解冻测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserstatusservice/unfreeze.html?memberId=100001100005
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/unfreeze", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseStatusUpdateResponse enterpriseUnfreeze(EnterpriseStatusUpdateRequest req) {
    	EnterpriseStatusUpdateResponse response = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
    	return response;
    }
    
    /**
     * 个人会员注销测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserstatusservice/cancel.html?memberId=100001100005
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseStatusUpdateResponse enterpriseCancel(EnterpriseStatusUpdateRequest req) {
    	EnterpriseStatusUpdateResponse response = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
    	return response;
    }

}
