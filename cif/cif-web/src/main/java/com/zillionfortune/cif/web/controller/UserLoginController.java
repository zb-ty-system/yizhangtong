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
import com.zillionfortune.cif.facade.user.UserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginResponse;

/**
 * ClassName: EnterpriseUserLoginController <br/>
 * Function: 个人_登入_http请求_控制器. <br/>
 * Date: 2016年11月22日 下午4:45:51 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/userloginservice")
public class UserLoginController {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserLoginServiceFacade userLoginServiceFacade;
	
	
	/**
	 * login:个人登入. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/userloginservice/login.html?userName=kaiyun&password=123456&loginSource=1
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public IndividualLoginResponse login(IndividualLoginRequest req) {
		
		log.info("UserLoginController.login.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginResponse response = null;
		response = userLoginServiceFacade.login(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("UserLoginController.login.resp:" + result);
    	
    	return response;
    } 
	
	/**
	 * auth:企业登入鉴权. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/userloginservice/auth.html?memberId=100001100002&accessToken=e40d01e0b37c40b38924bbc4406ba688
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public IndividualLoginAuthResponse auth(IndividualLoginAuthRequest req) {
		log.info("UserLoginController.auth.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginAuthResponse response = null;
		response = userLoginServiceFacade.auth(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("UserLoginController.auth.req:" + result);
    	
    	return response;
    } 
	
	/**
	 * userAuth:企业登出. <br/>
	 *
	 * 测试时浏览器地址栏输入：http://localhost:8080/cif/userloginservice/loginout.html?memberId=100001100002&accessToken=e40d01e0b37c40b38924bbc4406ba688
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
    @ResponseBody
    public IndividualLoginOutResponse loginout(IndividualLoginOutRequest req) {
		
		log.info("UserLoginController.loginout.req:" + JsonUtils.object2Json(req));
		
		IndividualLoginOutResponse response = null;
		response = userLoginServiceFacade.loginout(req);
    	String result = JsonUtils.object2Json(response);
    	
    	log.info("UserLoginController.loginout.req:" + result);
    	
    	return response;
    } 


}
