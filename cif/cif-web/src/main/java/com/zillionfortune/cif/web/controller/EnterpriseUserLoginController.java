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

import com.zillionfortune.cif.common.util.JsonUtils;
import com.zillionfortune.cif.facade.user.EnterpriseUserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginResponse;

/**
 * ClassName: EnterpriseUserLoginController <br/>
 * Function: 企业_登入_http请求_控制器. <br/>
 * Date: 2016年11月22日 下午4:45:51 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/enterpriseuserloginservice")
public class EnterpriseUserLoginController {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EnterpriseUserLoginServiceFacade enterpriseUserLoginServiceFacade;
	
	
	/**
	 * login:企业登入. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserloginservice/login.html?customerNo=100002&loginName=kaiyun&password=123456&loginSource=1
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseLoginResponse login(EnterpriseLoginRequest req) {
		
		log.info("EnterpriseUserLoginController.login.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginResponse response = null;
		response = enterpriseUserLoginServiceFacade.login(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("EnterpriseUserLoginController.login.resp:" + result);
    	
    	return response;
    } 
	
	/**
	 * auth:企业登入鉴权. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserloginservice/auth.html?memberId=100001100002&operatorId=2&accessToken=e40d01e0b37c40b38924bbc4406ba688
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseLoginAuthResponse auth(EnterpriseLoginAuthRequest req) {
		
		log.info("UserLoginController.auth.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginAuthResponse response = null;
		response = enterpriseUserLoginServiceFacade.auth(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("UserLoginController.auth.resp:" + result);
    	
    	return response;
    } 
	
	/**
	 * loginout:企业登出. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterpriseuserloginservice/loginout.html?memberId=100001100002&operatorId=2&accessToken=e40d01e0b37c40b38924bbc4406ba688
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseLoginOutResponse loginout(EnterpriseLoginOutRequest req) {
		
		log.info("UserLoginController.loginout.req:" + JsonUtils.object2Json(req));
		
		EnterpriseLoginOutResponse response = null;
		response = enterpriseUserLoginServiceFacade.loginout(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("UserLoginController.loginout.resp:" + result);
    	
    	return response;
    } 


}
