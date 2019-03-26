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

import com.zillionfortune.cif.facade.user.PasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordVerifyResponse;

/**
 * 个人会员重置/更新登录密码   Http接口
 * 
 * @author wangzinan
 *
 */
@Controller
@RequestMapping(value = "/passwordservice")
public class PasswordController {

    protected final Logger                  LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PasswordServiceFacade passwordServiceFacade;
    
    /**
     * 个人会员重置登录密码
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/passwordservice/retrieveloginpassword.html?memberId=100003&newPassword=XXX22yyy
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/retrieveloginpassword", method = RequestMethod.POST)
    @ResponseBody
    public IndividualLoginPasswordRetrieveResponse retrieveLoginPassword(IndividualLoginPasswordRetrieveRequest req) {
    	IndividualLoginPasswordRetrieveResponse response = passwordServiceFacade.retrieveLoginPassword(req);
    	return response;
    }
    
    /**
     * 个人会员更新登录密码
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/passwordservice/modifyloginpassword.html?memberId=100003&orgiPassword=wwwwww2&newPassword=aaa22bBb
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/modifyloginpassword", method = RequestMethod.POST)
    @ResponseBody
    public IndividualLoginPasswordModifyResponse modifyLoginPassword(IndividualLoginPasswordModifyRequest req) {
    	IndividualLoginPasswordModifyResponse response = passwordServiceFacade.modifyLoginPassword(req);
    	return response;
    }
    
    /**
     * setTradePassword:个人会员设置交易密码. <br/>
     * 
     * 测试输入地址：http://localhost:8080/cif/passwordservice/settradepassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/settradepassword", method = RequestMethod.POST)
    @ResponseBody
	public IndividualTradePasswordSetResponse setTradePassword(String memberId, String password) {
		
		return passwordServiceFacade.setTradePassword(memberId, password);
	}
    
    /**
     * retrieveTradePassword:个人会员重置交易密码. <br/>
     * 
     * 测试输入地址：http://localhost:8080/cif/passwordservice/retrievetradepassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/retrievetradepassword", method = RequestMethod.POST)
    @ResponseBody
	public IndividualTradePasswordRetrieveResponse retrieveTradePassword(String memberId, String newPassword) {
		
		return passwordServiceFacade.retrieveTradePassword(memberId, newPassword);
	}

    /**
     * modifyTradePassword:个人会员更新交易密码. <br/>
     *
     * 测试输入地址：http://localhost:8080/cif/passwordservice/modifyTradePassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @param orgiPassword
     * @return
     */
    @RequestMapping(value = "/modifytradepassword", method = RequestMethod.POST)
    @ResponseBody
	public IndividualTradePasswordModifyResponse modifyTradePassword(String memberId, String newPassword, String orgiPassword) {
		
		return passwordServiceFacade.modifyTradePassword(memberId, newPassword, orgiPassword);
	}

    /**
     * verifyTradePassword:个人会员验证交易密码. <br/>
     *
     * 测试输入地址：http://localhost:8080/cif/passwordservice/verifyTradePassword.html
     * @throws 
     * @param memberId
     * @param password
     * @return
     */
    @RequestMapping(value = "/verifytradepassword", method = RequestMethod.POST)
    @ResponseBody
	public IndividualTradePasswordVerifyResponse verifyTradePassword(String memberId, String password) {
		
		return passwordServiceFacade.verifyTradePassword(memberId, password);
	}
    
}
