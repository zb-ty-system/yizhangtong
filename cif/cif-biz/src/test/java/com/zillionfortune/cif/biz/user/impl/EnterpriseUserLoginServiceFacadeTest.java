/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.EnterpriseUserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginResponse;
import com.zillionfortune.cif.service.redis.EnterpriseTokenManager;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;

/**
 * ClassName: EnterpriseUserLoginServiceFacadeTest <br/>
 * Function: 企业_用户登入_单元测试类. <br/>
 * Date: 2016年11月23日 上午10:16:37 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserLoginServiceFacadeTest {
	
	@Autowired
	EnterpriseUserLoginServiceFacade enterpriseUserLoginServiceFacade;
	
	@Autowired
	EnterpriseMemberService enterpriseMemberService;
	
	@Autowired
	EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	EnterpriseTokenManager tokenManager;
	
	/**
	 * test_login1:登入_正常流程. <br/>
	 *
	 */
	@Test
	public void test_login1(){
//		String customerNo = "100002";//商户号,必输
//		String loginName = "kaiyun";//用户名，必输
//		String password = "E10ADC3949BA59ABBE56E057F20F883E";//密码
//		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		String customerNo = "100005";//商户号,必输
		String loginName = "13816947328";//用户名，必输
		String password = "03ADF148360D42274C42DECB95B43661aaa";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login2:登入_商户号不得为空. <br/>
	 *
	 */
	@Test
	public void test_login2(){
		String customerNo = "";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login21:登入_商户号错误. <br/>
	 *
	 */
	@Test
	public void test_login21(){
		String customerNo = "100002404";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login3:登入_用户名不得为空. <br/>
	 *
	 */
	@Test
	public void test_login3(){
		String customerNo = "100002";//商户号,必输
		String loginName = "";//用户名，必输
		String password = "123456";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login31:登入_用户名错误. <br/>
	 *
	 */
	@Test
	public void test_login31(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun_error";//用户名，必输
		String password = "123456";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login4:登入_登入来源不得为空. <br/>
	 *
	 */
	@Test
	public void test_login4(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login41:登入_登入来源只包含数字. <br/>
	 *
	 */
	@Test
	public void test_login41(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "z";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login42:登入_登入来源不存在. <br/>
	 *
	 */
	@Test
	public void test_login42(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "5";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login5:登入_若密码不为空时，正常授权. <br/>
	 *
	 */
	@Test
	public void test_login5(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login51:登入_若密码不为空时，密码错误. <br/>
	 *
	 */
	@Test
	public void test_login51(){
		String customerNo = "100002";//商户号,必输
		String loginName = "kaiyun";//用户名，必输
		String password = "123456_error";//密码
		String loginSource = "1";//登入来源：1pc 2Android 3ios，必输
		
		//封装请求参数
		EnterpriseLoginRequest req = new EnterpriseLoginRequest();
		req.setCustomerNo(customerNo);
		req.setLoginName(loginName);;
		req.setPassword(password);
		req.setLoginSource(loginSource);
		//执行
		EnterpriseLoginResponse  resp = enterpriseUserLoginServiceFacade.login(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	
	
	
	/**
	 * test_auth1:登入鉴权_正常. <br/>
	 *
	 */
	@Test
	public void test_auth1(){
//		String memberId = "100001100002";
//		String operatorId = "2";
		
		String memberId = "EM201612200048241323176703";
		String operatorId = "22";
		
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			 
			//封装请求参数
			EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_auth2:登入鉴权_memberId不得为空. <br/>
	 *
	 */
	@Test
	public void test_auth2(){
		String memberId = "";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * test_auth21:登入鉴权_memberId不存在或错误. <br/>
	 *
	 */
	@Test
	public void test_auth21(){
		String memberId = "100001100002_error";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_auth3:登入鉴权_operatorId不得为空. <br/>
	 *
	 */
	@Test
	public void test_auth3(){
		String memberId = "100001100002";
		String operatorId_error = "";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId_error);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_auth31:登入鉴权_operatorId错误. <br/>
	 *
	 */
	@Test
	public void test_auth31(){
		String memberId = "100001100002";
		String operatorId_error = "2_error";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId_error);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_auth4:登入鉴权_accessToken不得为空. <br/>
	 *
	 */
	@Test
	public void test_auth4(){
		String memberId = "100001100002";
		String operatorId = "2";
		String accessToken = "";
		
		//封装请求参数
		EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
		req.setMemberId(memberId);
		req.setOperatorId(operatorId);
		req.setAccessToken(accessToken);
		//执行
		EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth41:登入鉴权_accessToken错误. <br/>
	 *
	 */
	@Test
	public void test_auth41(){
		String memberId = "100001100002";
		String operatorId = "2";
		String accessToken = "43b54922664e456da3cdb438d6881618_error";
		
		//封装请求参数
		EnterpriseLoginAuthRequest req = new EnterpriseLoginAuthRequest();
		req.setMemberId(memberId);
		req.setOperatorId(operatorId);
		req.setAccessToken(accessToken);
		//执行
		EnterpriseLoginAuthResponse resp = enterpriseUserLoginServiceFacade.auth(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	
	/**
	 * test_loginout1:登出_正常. <br/>
	 *
	 */
	@Test
	public void test_loginout1(){
//		String memberId = "100001100002";
//		String operatorId = "2";
		
		String memberId = "EM201612200048241323176703";
		String operatorId = "22";
		
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout2:登出_memberId不得为空. <br/>
	 *
	 */
	@Test
	public void test_loginout2(){
		String memberId = "";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout21:登出_memberId错误. <br/>
	 *
	 */
	@Test
	public void test_loginout21(){
		String memberId = "100001100002_error";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout3:登出_operatorId不得为空. <br/>
	 *
	 */
	@Test
	public void test_loginout3(){
		String memberId = "100001100002";
		String operatorId_error = "";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId_error);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout31:登出_operatorId错误. <br/>
	 *
	 */
	@Test
	public void test_loginout31(){
		String memberId = "100001100002";
		String operatorId_error = "2_error";
		String operatorId = "2";
		try {
			String accessToken = tokenManager.getTokenByKey(operatorId);
			
			//封装请求参数
			EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
			req.setMemberId(memberId);
			req.setOperatorId(operatorId_error);
			req.setAccessToken(accessToken);
			//执行
			EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
			//输出
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout4:登出_accessToken不得为空. <br/>
	 *
	 */
	@Test
	public void test_loginout4(){
		String memberId = "100001100002";
		String operatorId = "2";
		String accessToken = "";
		
		//封装请求参数
		EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
		req.setMemberId(memberId);
		req.setOperatorId(operatorId);
		req.setAccessToken(accessToken);
		//执行
		EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_loginout41:登出_accessToken错误. <br/>
	 *
	 */
	@Test
	public void test_loginout41(){
		String memberId = "100001100002";
		String operatorId = "2";
		String accessToken = "43b54922664e456da3cdb438d6881618_error";
		
		//封装请求参数
		EnterpriseLoginOutRequest req = new EnterpriseLoginOutRequest();
		req.setMemberId(memberId);
		req.setOperatorId(operatorId);
		req.setAccessToken(accessToken);
		//执行
		EnterpriseLoginOutResponse resp = enterpriseUserLoginServiceFacade.loginout(req);
		//输出
		System.out.println(JSON.toJSONString(resp));
	}

}
