package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.UserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoQueryRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserInfoQueryFacadeTest {

	@Autowired
	UserInfoQueryServiceFacade userInfoQueryServiceFacade;

	/** queryUserInfo test begin **/
	@Test
	public void queryUserInfoTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserInfo(null)));
	}

	@Test
	public void queryUserInfoTestMemberIdIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserInfo(param)));
	}

	@Test
	public void queryUserInfoTestObjIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		param.setMemberId("100000001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserInfo(param)));
	}

	@Test
	public void queryUserInfoTest() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		param.setMemberId("100001100001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserInfo(param)));
	}

	/** queryUserInfo test end **/

	/** queryIndividualInfo test begin **/
	@Test
	public void queryIndividualInfoTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryIndividualInfo(null)));
	}

	@Test
	public void queryIndividualInfoTestParamValAllIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryIndividualInfo(param)));
	}

	@Test
	public void queryIndividualInfoTestObjIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		param.setMemberId("100000001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryIndividualInfo(param)));

		IndividualInfoQueryRequest param1 = new IndividualInfoQueryRequest();
		param1.setCustomerId("1000001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryIndividualInfo(param1)));
	}

	@Test
	public void queryIndividualInfoTest() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		//param.setMemberId("100001100001");
		param.setCustomerId("1000011");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryIndividualInfo(param)));
	}

	/** queryIndividualInfo test end **/

	/** queryUserAuthInfo test begin **/
	@Test
	public void queryUserAuthInfoTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserAuthInfo(null)));
	}

	@Test
	public void queryUserAuthInfoTestParamValAllIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserAuthInfo(param)));
	}

	@Test
	public void queryUserAuthInfoTestObjIsNull() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		param.setMemberId("100000001");
		param.setCustomerId("100001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserAuthInfo(param)));

		IndividualInfoQueryRequest param1 = new IndividualInfoQueryRequest();
		param1.setCustomerId("100001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserAuthInfo(param1)));
	}

	@Test
	public void queryUserAuthInfoTest() {
		IndividualInfoQueryRequest param = new IndividualInfoQueryRequest();
		param.setMemberId("100001100001");
		param.setCustomerId("100001");
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserAuthInfo(param)));
	}

	/** queryUserAuthInfo test end **/
}
