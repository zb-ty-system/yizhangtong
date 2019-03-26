package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.dal.dao.EnterpriseInfoDao;
import com.zillionfortune.cif.facade.user.EnterpriseUserAuthServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserAuthServiceFacadeImplTest {

	@Autowired
	private EnterpriseUserAuthServiceFacade facade;
	@Autowired
	private EnterpriseInfoDao dao;
	
	@Test
	public void realNameAuthParamNull(){
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(null)));
	}
	
	@Test
	public void realNameAuthParamIsEmpty1(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	@Test
	public void realNameAuthParamIsEmpty2(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setCertificateType("1");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	@Test
	public void realNameAuthParamIsEmpty3(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setCertificateNo("E90u789999999999");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	@Test
	public void realNameAuthParamIsEmpty4(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthParamFormatError1(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("1a");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthParamFormatError2(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("11");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthMemberNotFound(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("1");
		req.setMemberId("_201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthMemberIsAuth(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("1");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthParamMatchFail1(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("2");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthParamMatchFail2(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("_资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("1");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthParamMatchFail3(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("_90u789999999999");
		req.setCertificateType("1");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
	
	@Test
	public void realNameAuthTest(){
		EnterpriseUserAuthServiceRequest req = new EnterpriseUserAuthServiceRequest();
		req.setEnterpriseName("资邦金服");
		req.setCertificateNo("E90u789999999999");
		req.setCertificateType("1");
		req.setMemberId("E201611301129288751757919");
		System.out.println(JSONObject.toJSONString(facade.realNameAuth(req)));
	}
}
