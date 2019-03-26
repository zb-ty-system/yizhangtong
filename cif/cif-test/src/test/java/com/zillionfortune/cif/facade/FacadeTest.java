package com.zillionfortune.cif.facade;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.UserStatusServiceFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class FacadeTest {

	@Autowired
	UserStatusServiceFacade userStatusServiceFacade;
	
	@Test
	public void test(){
		 Map<String, String> data = new HashMap<String, String>();
	     data.put("mobile", "1376475250");
	     //data.put("age", "28");
	  
		
		
	}
}
