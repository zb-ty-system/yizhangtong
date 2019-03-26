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
import com.zillionfortune.cif.facade.user.IndividualServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class IndividualServiceFacadeImplTest{

	private static Logger log = LoggerFactory.getLogger(EnterpriseServiceFacadeImplTest.class);
	
	@Autowired
	IndividualServiceFacade individualServiceFacade;
	
	@Test
	public void testRegister(){
		
		IndividualRegisterRequest req = new IndividualRegisterRequest();
	
        req.setMobile("137647525567889");
        req.setRegisterSource("1");
      
		IndividualRegisterResponse resp = individualServiceFacade.register(req);
		
		System.out.println("IndividualServiceFacadeImplTest.testRegister.resp:"+JSON.toJSONString(resp));
		log.info(JSON.toJSONString(resp));
	}
	
	@Test
	public void testRegisterInfoUpdate(){
		
		IndividualRegisterInfoUpdateRequest req = new IndividualRegisterInfoUpdateRequest();
		IndividualRegisterInfoUpdateResponse resp = individualServiceFacade.registerInfoUpdate(req);
		 
		log.info(JSON.toJSONString(resp));
	}
	
	@Test
	public void testIndividualInfoUpdate(){
		
		IndividualInfoUpdateRequest req = new IndividualInfoUpdateRequest();
		req.setCustomerId("20161123");
		req.setAge("99");
		req.setGender("2");
		req.setWorkYears("56");
		req.setMarriageStatus("0");
		req.setAnnualSalary("5623.12");
	
		IndividualInfoUpdateResponse resp = individualServiceFacade.individualInfoUpdate(req);
		 
		log.info(JSON.toJSONString(resp));
	}

}
