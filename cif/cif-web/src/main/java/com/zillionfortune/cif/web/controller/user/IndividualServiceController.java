/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.IndividualServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterResponse;

/**
 * ClassName: IndividualServiceController <br/>
 * Function: 个人注册服务controller层. <br/>
 * Date: 2016年12月9日 下午2:03:28 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/individualservice")
public class IndividualServiceController {

	private static Logger log = LoggerFactory.getLogger(IndividualServiceController.class);

	@Autowired
	IndividualServiceFacade individualServiceFacade;
	
	/**
     * 个人会员注册
     * 访问链接：http://cif.zillionfortune.com/individualservice/register.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public IndividualRegisterResponse register(IndividualRegisterRequest req) {
    	
    	IndividualRegisterResponse response = individualServiceFacade.register(req);
    	
    	return response;
    }
    
    /**
     * 个人客户注册信息修改
     * 访问链接：http://cif.zillionfortune.com/individualservice/registerinfoupdate.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/registerinfoupdate", method = RequestMethod.POST)
    @ResponseBody
    public IndividualRegisterInfoUpdateResponse registerInfoUpdate(IndividualRegisterInfoUpdateRequest req) {
    	
    	IndividualRegisterInfoUpdateResponse response = individualServiceFacade.registerInfoUpdate(req);
    	
    	return response;
    }
    
    /**
     * 个人客户扩展信息修改
     * 访问链接：http://cif. zillionfortune.com/individualservice/individualinfoupdate.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/individualinfoupdate", method = RequestMethod.POST)
    @ResponseBody
    public IndividualInfoUpdateResponse individualInfoUpdate(IndividualInfoUpdateRequest req) {
    	
    	IndividualInfoUpdateResponse response = individualServiceFacade.individualInfoUpdate(req);
    	
    	return response;
    }
    
}
