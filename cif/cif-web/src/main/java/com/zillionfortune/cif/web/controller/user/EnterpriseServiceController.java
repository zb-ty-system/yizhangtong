/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.EnterpriseQualificationFacade;
import com.zillionfortune.cif.facade.user.EnterpriseServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterResponse;

/**
 * ClassName: EnterpriseServiceController <br/>
 * Function: 企业注册服务controller层. <br/>
 * Date: 2016年12月9日 下午2:03:28 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/enterpriseservice")
public class EnterpriseServiceController {

	@Autowired
	EnterpriseServiceFacade enterpriseServiceFacade;
	
	@Autowired
	EnterpriseQualificationFacade enterpriseQualificationFacade;
	
	/**
     * 企业客户注册
     * 访问链接：http://cif.zillionfortune.com/enterpriseservice/register.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseRegisterResponse register(EnterpriseRegisterRequest req) {
    	
    	EnterpriseRegisterResponse response = enterpriseServiceFacade.register(req);
    	
    	return response;
    }
    
    /**
     * 企业会员添加操作员
     * 访问链接：http://cif.zillionfortune.com/enterpriseservice/registeropertor.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/registeropertor", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseRegisterOpertorResponse registerOpertor(EnterpriseRegisterOpertorRequest req) {
    	
    	EnterpriseRegisterOpertorResponse response = enterpriseServiceFacade.registerOpertor(req);
    	
    	return response;
    }
    
    /**
     * 企业客户注册信息修改
     * 访问链接：http://cif. zillionfortune.com/enterpriseservice/registerinfoupdate.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/registerinfoupdate", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseRegisterInfoUpdateResponse registerInfoUpdate(EnterpriseRegisterInfoUpdateRequest req) {
    	
    	EnterpriseRegisterInfoUpdateResponse response = enterpriseServiceFacade.registerInfoUpdate(req);
    	
    	return response;
    }
    
    /**
     * 企业客户扩展信息修改
     * 访问链接：http://cif.zillionfortune.com/enterpriseservice/enterpriseinfoupdate.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/enterpriseinfoupdate", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseInfoUpdateResponse enterpriseInfoUpdate(EnterpriseInfoUpdateRequest req) {
    	
    	EnterpriseInfoUpdateResponse response = enterpriseServiceFacade.enterpriseInfoUpdate(req);
    	
    	return response;
    }
    
    /**
     * 企业资质信息修改
     * 访问链接：http://cif.zillionfortune.com/enterpriseservice/qualificationupdate.html
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/qualificationupdate", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseQualificationUpdateResponse qualificationUpdate(EnterpriseQualificationUpdateRequest req){
    	return enterpriseQualificationFacade.qualificationUpdate(req);
    }
    
    
}
