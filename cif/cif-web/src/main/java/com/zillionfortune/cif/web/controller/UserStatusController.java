package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.UserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateResponse;

/**
 * 个人会员冻结/解冻/注销   Http接口
 * 
 * @author wangzinan
 *
 */
@Controller
@RequestMapping(value = "/userstatusservice")
public class UserStatusController {

    protected final Logger                  LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
	UserStatusServiceFacade userStatusServiceFacade;
    
    /**
     * 个人会员冻结测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/userstatusservice/frozen.html?memberId=100003
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/frozen", method = RequestMethod.POST)
    @ResponseBody
    public UserStatusUpdateResponse userFrozen(UserStatusUpdateRequest req) {
    	UserStatusUpdateResponse response = userStatusServiceFacade.userFrozen(req);
    	return response;
    }
    
    /**
     * 个人会员解冻测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/userstatusservice/unfreeze.html?memberId=100004
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/unfreeze", method = RequestMethod.POST)
    @ResponseBody
    public UserStatusUpdateResponse userUnfreeze(UserStatusUpdateRequest req) {
    	UserStatusUpdateResponse response = userStatusServiceFacade.userUnfreeze(req);
    	return response;
    }
    
    /**
     * 个人会员注销测试
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/userstatusservice/cancel.html?memberId=100004
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public UserStatusUpdateResponse userCancel(UserStatusUpdateRequest req) {
    	UserStatusUpdateResponse response = userStatusServiceFacade.userCancel(req);
    	return response;
    }

}
