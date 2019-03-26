package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.CurrentEnterpriseUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.CurrentUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class CurrentUserQueryServiceFacadeTest {

	@Autowired
	private CurrentUserQueryServiceFacade currentUserQueryServiceFacade;
	
	@Autowired
	private CurrentEnterpriseUserQueryServiceFacade currentEnterpriseUserQueryServiceFacade;
	
	@Test
	public void queryTestparamIsNull() {
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(null)));
	}
	
	@Test
	public void queryTestparamIsAllNull() {
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(new CurrentUserQueryServiceRequest())));
		
		CurrentUserQueryServiceRequest req1 = new CurrentUserQueryServiceRequest();
		req1.setAccessToken("1122gdfsfga45457");
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(req1)));
		
		CurrentUserQueryServiceRequest req2 = new CurrentUserQueryServiceRequest();
		req2.setMemberId("memberId");
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(req2)));
	}
	
	@Test
	public void queryTestTokenIsError() {
		CurrentUserQueryServiceRequest req1 = new CurrentUserQueryServiceRequest();
		req1.setAccessToken("3ab79f52ee044deebc6fe0487c79a677_error");
		req1.setMemberId("100001100001");
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(req1)));
		
		CurrentUserQueryServiceRequest req2 = new CurrentUserQueryServiceRequest();
		req2.setAccessToken("3ab79f52ee044deebc6fe0487c79a677");
		req2.setMemberId("100001100001_error");
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(req2)));
	}
	
	@Test
	public void queryTest() {
		CurrentUserQueryServiceRequest req1 = new CurrentUserQueryServiceRequest();
		req1.setAccessToken("3ab79f52ee044deebc6fe0487c79a677");
		req1.setMemberId("100001100001");
		System.out.println(JSONObject.toJSONString(currentUserQueryServiceFacade.query(req1)));
	}
	
	//------------------------------------------------ 以下 企业
	@Test
	public void queryEntTestparamIsNull() {
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(null)));
	}
	
	@Test
	public void queryEntTestparamIsAllNull() {
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(new CurrentEnterpriseUserQueryRequest())));
		
		CurrentEnterpriseUserQueryRequest req1 = new CurrentEnterpriseUserQueryRequest();
		req1.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		req1.setOperatorId("1");
		// req1.setMemberId("memberId");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req1)));
		
		CurrentEnterpriseUserQueryRequest req2 = new CurrentEnterpriseUserQueryRequest();
		req2.setMemberId("memberId");
		req2.setOperatorId("1");
		// 		req2.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req2)));
		
		CurrentEnterpriseUserQueryRequest req3 = new CurrentEnterpriseUserQueryRequest();
		req3.setOperatorId("1");
		// req3.setMemberId("memberId");
		// req3.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req3)));
	}
	
	@Test
	public void queryEntTestTokenIsError() {
		CurrentEnterpriseUserQueryRequest req1 = new CurrentEnterpriseUserQueryRequest();
		req1.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46_error");
		req1.setMemberId("100000000001");
		req1.setOperatorId("1");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req1)));
		
		CurrentEnterpriseUserQueryRequest req2 = new CurrentEnterpriseUserQueryRequest();
		req2.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		req2.setMemberId("100000000001_error");
		req2.setOperatorId("1");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req2)));
	}
	
	@Test
	public void queryEntTestTokenIsUnLogin() {
		CurrentEnterpriseUserQueryRequest req1 = new CurrentEnterpriseUserQueryRequest();
		req1.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46_error");
		req1.setMemberId("100000000001_bak");
		req1.setOperatorId("1");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req1)));
		
	}
	
	@Test
	public void queryEntTest() {
		CurrentEnterpriseUserQueryRequest req1 = new CurrentEnterpriseUserQueryRequest();
		req1.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		req1.setMemberId("100000000001");
		req1.setOperatorId("1");
		System.out.println(JSONObject.toJSONString(currentEnterpriseUserQueryServiceFacade.query(req1)));
	}
	
	
	/*public static void main(String[] args) {
		BaseResponse bean = new BaseResponse();
		bean.setRespCode("999999");
		CurrentEnterpriseUserQueryRequest req1 = new CurrentEnterpriseUserQueryRequest();
		req1.setAccessToken("86bb47fe967a4ff9be0088bdbedbb46");
		req1.setMemberId("100000000001");
		req1.setOperatorId("1");
		bean.setData(req1);
		
		System.out.println(JSONObject.toJSONString(req1));
	}*/
}
