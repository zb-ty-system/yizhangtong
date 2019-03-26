package com.zillionfortune.cif.biz.user.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.biz.common.util.BusinessFlowNoUtils;
import com.zillionfortune.cif.common.constants.CommonConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class CommonTest{
	
	@Resource
	BusinessFlowNoUtils businessFlowNoUtils;

	 @Test
	public void testGenerateNo(){

		try {
			String flow =businessFlowNoUtils.generateSerialNo("E", CommonConstants.ENTERPRISE_MEMBER,
					CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO, 
					CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO_NO_FORMAT,
					CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO_START_ID);
			
			System.out.println("==========流水号"+flow);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
