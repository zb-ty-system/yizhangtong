package com.zillionfortune.cif.biz.user.card.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseBindCardServiceFacadeImplTest {

	@Autowired
	private EnterpriseBindCardServiceFacade facade;
	
	@Test
	public void bindCardTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.bindCard(null)));
	}
	
	@Test
	public void bindCardTestParamIsEmpty() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301602519329152222");
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestMemberStatusUnnomarl() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301551268137200221");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestCardBinded() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301602519329152222");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTest() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301551268137200221");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	@Test
	public void unBindCardTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.unBindCard(null)));
	}
	
	@Test
	public void unBindCardTestParamIsEmpty() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		//req.setMemberId("E201611301602519329152222");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestMemberNotFound() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301602519329152222_");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestMemberStatusUnnomarl() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301551268137200221");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestBankAccountIsnull() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301602519329152222");
		req.setBankAccountNo("62258823324324324234");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTest() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("EM201612201129335535205108");
		req.setBankAccountNo("62258823324324324234");
		req.setBankAccountName("test");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void queryEnterpriseBindCardTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.queryEnterpriseBindCard(null)));
	}
	
	@Test
	public void queryEnterpriseBindCardTestParamIsEmpty() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		//req.setMemberId("E201611301602519329152222");
		System.out.println(JSONObject.toJSONString(facade.queryEnterpriseBindCard(req)));
	}
	
	@Test
	public void queryEnterpriseBindCardTestMemberNotfound() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301602519329152222_");
		System.out.println(JSONObject.toJSONString(facade.queryEnterpriseBindCard(req)));
	}
	
	@Test
	public void queryEnterpriseBindCardTest() {
		EnterpriseBindCardRequest req = new EnterpriseBindCardRequest();
		req.setMemberId("E201611301551268137200221");
		System.out.println(JSONObject.toJSONString(facade.queryEnterpriseBindCard(req)));
	}
	
	@Test
	public void findBankAccountNoTest() {
		System.out.println(JSONObject.toJSONString(facade.findBankAccountNo("123123123123")));
	}
	
}
