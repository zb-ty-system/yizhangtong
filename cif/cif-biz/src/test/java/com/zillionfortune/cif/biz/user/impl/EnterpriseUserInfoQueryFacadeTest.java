package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserInfoQueryFacadeTest {

	@Autowired
	EnterpriseUserInfoQueryServiceFacade enterpriseUserInfoQueryServiceFacade;

	/** queryUserInfo test begin **/
	@Test
	public void queryUserInfoTestParamIsNull() {
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryUserInfo(null)));
	}

	@Test
	public void queryUserInfoTestParamIsAllNull() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryUserInfo(param)));
	}

	@Test
	public void queryUserInfoTestObjIsNull() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		param.setMemberId("100000001");
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(param)));
	}

	@Test
	public void queryUserInfoTest() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		//param.setMemberId("EM201612200048241323176703");
		param.setOperatorId(22L);
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryUserInfo(param)));
	}

	/** queryUserInfo test end **/

	/** queryInfo test begin **/
	@Test
	public void queryInfoTestParamIsNull() {
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(null)));
	}

	@Test
	public void queryInfoTestParamIsAllNull() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(param)));
	}

	@Test
	public void queryInfoTestObjIsNull() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		param.setMemberId("100001100001");
		param.setCustomerId("1000001");
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(param)));
	}

	@Test
	public void queryInfoTest() {
		EnterpriseUserInfoQueryRequest param = new EnterpriseUserInfoQueryRequest();
		param.setMemberId("EM201612221551183627645252");
		//param.setCustomerId("E201611301129288724535577");
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(param)));

		/*EnterpriseUserInfoQueryRequest param1 = new EnterpriseUserInfoQueryRequest();
		param1.setCustomerId("100001");
		System.out.println(JSONObject
				.toJSONString(enterpriseUserInfoQueryServiceFacade
						.queryInfo(param1)));*/
	}
	/** queryInfo test end **/
	
	@Test
	public void queryUserAuthInfoParamIsNull() {
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryUserAuthInfo(null)));
	}
	
	@Test
	public void queryUserAuthParamIsEmpty() {
		EnterpriseUserInfoQueryRequest request = new EnterpriseUserInfoQueryRequest();
		//request.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryUserAuthInfo(request)));
	}
	
	@Test
	public void queryUserAuthMemberNotFound() {
		EnterpriseUserInfoQueryRequest request = new EnterpriseUserInfoQueryRequest();
		request.setMemberId("_E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryUserAuthInfo(request)));
	}
	
	@Test
	public void queryUserAuthInfoTest() {
		EnterpriseUserInfoQueryRequest request = new EnterpriseUserInfoQueryRequest();
		request.setMemberId("EM201612221551183627645252");
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryUserAuthInfo(request)));
	}
	@Test
	public void queryUserAuthInfoByPageTest() {
		EntUserAuthInfoByPageQueryRequest req =new EntUserAuthInfoByPageQueryRequest();
		//req.setMemberId("EM201612221928342453966729");
		//req.setLegalPersonAuditStatus(2);
		//req.setEnterpriseAuditStatus(0);
		//req.setMobile("19764752550");
		//req.setEnterpriseName("资邦");
		//req.setLegalPersonName("张三");
		req.setCustomerNo("100050");
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryUserAuthInfoByPage(req)));
	}
	
	@Test
	public void queryInfoByPageTest() {
		EntUserQueryInfoByPageQueryRequest req = new EntUserQueryInfoByPageQueryRequest();
		//req.setMobile("13764752550");
		//req.setCustomerNo("100034");
		// req.setEnterpriseName("集团");
		//req.setLegalPersonAuditStatus(1);
		//req.setAuthStatus(3);
		//req.setRegisterTimeStart("2016-11-21");
		//req.setRegisterTimeEnd("2016-12-20");
		//req.setLegalPersonName("郑润龙1");
		//req.setMemberId("EM201612200048241323176703");
		//req.setEnterpriseAuditStatus(0);
		req.setPageSize(5);
		req.setCurrentPage(2);
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.queryInfoByPage(req)));
	}
	
	@Test
	public void findUserNameIsExistTest() {
		System.out.println(JSONObject.toJSONString(enterpriseUserInfoQueryServiceFacade.findUserNameIsExist("")));
	}
	
	@Test
	public void queryInfoByCertTypeAndNoTest() {
		
		EnterpriseInfoQueryByCertTypeNoRequest req = new EnterpriseInfoQueryByCertTypeNoRequest();
		req.setCertificateType(1);
		req.setCertificateNo("123123123123");
		EnterpriseInfoResponse resp = enterpriseUserInfoQueryServiceFacade.queryInfoByCertTypeAndNo(req);
		
		System.out.println(JSONObject.toJSONString(resp));
		
	}
	
}
