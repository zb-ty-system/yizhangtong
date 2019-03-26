/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.EnterpriseUserAuditServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditRequest;

/**
 * ClassName: EnterpriseUserAuditServiceFacadeImplTest <br/>
 * Function: 企业会员状态审核测试类. <br/>
 * Date: 2016年12月28日 下午1:42:02 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserAuditServiceFacadeImplTest {

	@Autowired
	private EnterpriseUserAuditServiceFacade facade;
	@Test
	public void auditTest() {
		EnterpriseUserAuditRequest req = new EnterpriseUserAuditRequest();
		req.setMemberId("EM201612221551183627645252");
		req.setEnterpriseAuditStatus(2);
		req.setEnterpriseAuditComment("不通过哦");
		req.setLegalPersonAuditComment("OK 欧克");
		System.out.println(JSONObject.toJSONString(facade.audit(req)));
	}
}
