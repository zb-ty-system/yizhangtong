package com.zillionfortune.cif.facade;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.UserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserInfoQueryFacadeTest {

	@Autowired
	UserInfoQueryServiceFacade userInfoQueryServiceFacade;

	private EnterpriseUserInfoQueryRequest param;

	@Before
	public void setUp() {
		param = new EnterpriseUserInfoQueryRequest();
	}

	@Test
	public void queryUserInfoTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(userInfoQueryServiceFacade
				.queryUserInfo(null)));
	}

}
