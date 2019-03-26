package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordVerifyResponse;

/**
 * 企业会员重置/更新登录密码   Http接口
 * 
 * @author wangzinan
 *
 */
@Controller
@RequestMapping(value = "/enterprisepasswordservice")
public class EnterprisePasswordController {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EnterprisePasswordServiceFacade enterprisePasswordServiceFacade;
    
    /**
     * 企业会员重置登录密码
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterprisepasswordservice/retrieveloginpassword.html?memberId=100001100004&operatorId=5&newPassword=XXX22yyy
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/retrieveloginpassword", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseLoginPasswordRetrieveResponse retrieveLoginPassword(EnterpriseLoginPasswordRetrieveRequest req) {
    	EnterpriseLoginPasswordRetrieveResponse response = enterprisePasswordServiceFacade.retrieveLoginPassword(req);
    	return response;
    }
    
    /**
     * 企业会员更新登录密码
     * 
     * 测试时浏览器地址栏输入：http://localhost:8080/cif/enterprisepasswordservice/modifyloginpassword.html?memberId=100001100004&operatorId=5&orgiPassword=wwwwww3&newPassword=aaa22bBb
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/modifyloginpassword", method = RequestMethod.POST)
    @ResponseBody
    public EnterpriseLoginPasswordModifyResponse modifyLoginPassword(EnterpriseLoginPasswordModifyRequest req) {
    	EnterpriseLoginPasswordModifyResponse response = enterprisePasswordServiceFacade.modifyLoginPassword(req);
    	return response;
    }
    
    /**
     * retrieveTradePassword:企业会员重置交易密码. <br/>
     * 
     * 测试输入地址：http://localhost:8080/cif/enterprisepasswordservice/retrievetradepassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/retrievetradepassword", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseTradePasswordRetrieveResponse retrieveTradePassword(String memberId, String newPassword) {
		
		return enterprisePasswordServiceFacade.retrieveTradePassword(memberId, newPassword);
	}

    /**
     * modifyTradePassword:企业会员更新交易密码. <br/>
     *
     * 测试输入地址：http://localhost:8080/cif/enterprisepasswordservice/modifyTradePassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @param orgiPassword
     * @return
     */
    @RequestMapping(value = "/modifytradepassword", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseTradePasswordModifyResponse modifyTradePassword(String memberId, String newPassword, String orgiPassword) {
		
		return enterprisePasswordServiceFacade.modifyTradePassword(memberId, newPassword, orgiPassword);
	}

    /**
     * verifyTradePassword:企业会员验证交易密码. <br/>
     *
     * 测试输入地址：http://localhost:8080/cif/enterprisepasswordservice/verifyTradePassword.html
     * @throws 
     * @param memberId
     * @param password
     * @return
     */
    @RequestMapping(value = "/verifytradepassword", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseTradePasswordVerifyResponse verifyTradePassword(String memberId, String password) {
		
		return enterprisePasswordServiceFacade.verifyTradePassword(memberId, password);
	}
    
    /**
     * setTradePassword:企业会员设置交易密码. <br/>
     * 
     * 测试输入地址：http://localhost:8080/cif/enterprisepasswordservice/settradepassword.html
     * @throws 
     * @param memberId
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/settradepassword", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseTradePasswordSetResponse setTradePassword(String memberId, String password) {
		
		return enterprisePasswordServiceFacade.setTradePassword(memberId, password);
	}
    
}
