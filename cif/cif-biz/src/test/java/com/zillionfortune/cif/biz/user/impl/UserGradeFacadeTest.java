package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.UserGradeServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserGradeFacadeTest {

	@Autowired
	UserGradeServiceFacade userGradeServiceFacade;

	/** updateGrade test begin **/
	@Test
	public void updateGradeTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.updateGrade(null)));
	}

	@Test
	public void updateGradeTestParamValIsNull() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setGrade("1");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.updateGrade(request)));
	}

	@Test
	public void updateGradeTestMemberIsNull() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setGradeType("1");
		request.setMemberId("1000001");
		request.setGrade("1");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.updateGrade(request)));
	}

	@Test
	public void updateGradeTest() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setGradeType("1");
		request.setMemberId("100001100001");
		request.setGrade("1");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.updateGrade(request)));
	}

	/** updateGrade test end **/

	/** queryGrade test begin **/
	@Test
	public void queryGradeTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.queryGrade(null)));
	}

	@Test
	public void queryGradeTestParamValIsNull() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setMemberId("100001");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.queryGrade(request)));
	}

	@Test
	public void queryGradeTestMemberIsNull() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setGradeType("1");
		request.setMemberId("1000001");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.queryGrade(request)));
	}

	@Test
	public void queryGradeTest() {
		UserGradeServiceRequest request = new UserGradeServiceRequest();
		request.setGradeType("11");
		request.setMemberId("100001");
		System.out.println(JSONObject.toJSONString(userGradeServiceFacade
				.queryGrade(request)));
	}
	/** queryGrade test end **/

}
