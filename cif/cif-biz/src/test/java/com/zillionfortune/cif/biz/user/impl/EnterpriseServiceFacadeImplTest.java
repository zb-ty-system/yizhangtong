package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.EnterpriseServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseServiceFacadeImplTest{

	@Autowired
	EnterpriseServiceFacade enterpriseServiceFacade;
	
	private static Logger log = LoggerFactory.getLogger(EnterpriseServiceFacadeImplTest.class);
	
	@Test
	public void testRegister(){
		EnterpriseRegisterRequest req = new EnterpriseRegisterRequest();
		req.setEmail("rlzheng@si.com");
		req.setMobile("138353632123");
		req.setPassword("123456OOOXXX");
		req.setCertificateType("1");
		req.setLegalPersonCertificateType("1");
		req.setLegalPersonCertificateNo("T56787893838");
		req.setLegalPersonName("郑润龙");
		req.setCertExpDate("2016-12-10");
		req.setPostCode("2210120");
		req.setEnterpriseName("资邦集团");
		req.setRegisterAddress("上海市杨高南路");
		req.setMobile("13764752550");
		req.setCertificateNo("E90u789456");
		req.setLegalPersonCertExpDate("2016-10-12");
		req.setPhone("15512512538");
		EnterpriseRegisterResponse resp = enterpriseServiceFacade.register(req);
		System.out.println(JSON.toJSONString(resp));
		log.info(JSON.toJSONString(resp));
	}
	
	@Test
	public void testRegisterOpertor(){
		EnterpriseRegisterOpertorRequest req = new EnterpriseRegisterOpertorRequest();
		req.setMobile("138383838");
		req.setCustomerNo("100007");
		
		EnterpriseRegisterOpertorResponse resp = enterpriseServiceFacade.registerOpertor(req);
		
		log.info(JSON.toJSONString(resp));
	}
	
	@Test
	public void testRegisterInfoUpdate(){
		EnterpriseRegisterInfoUpdateRequest req = new EnterpriseRegisterInfoUpdateRequest();
		EnterpriseRegisterInfoUpdateResponse resp = enterpriseServiceFacade.registerInfoUpdate(req);
		
		log.info(JSON.toJSONString(resp));
	}
	
	@Test
	public void testEnterpriseInfoUpdate(){
		EnterpriseInfoUpdateRequest req = new EnterpriseInfoUpdateRequest();
		req.setMemberId("EM201612200048241323176703");
		req.setCertificateType(1);
		req.setCertificateNo("E90u123456");
		req.setPhone("13816947328");
		req.setPostCode("200000");
		req.setCertExpDate("2018-01-01");
		req.setLegalPersonCertExpDate("2019-01-01");
		EnterpriseInfoUpdateResponse resp = enterpriseServiceFacade.enterpriseInfoUpdate(req);
		
		
		log.info(JSON.toJSONString(resp));
	}
	 
}
