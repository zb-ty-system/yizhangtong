package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.EnterpriseUserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserStatusServiceFacadeImplTest {
	@Autowired
	EnterpriseUserStatusServiceFacade enterpriseUserStatusServiceFacade;
	
	/**
	 * 企业会员冻结测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void frozenTest1(){
		EnterpriseStatusUpdateRequest req = null;
	  
	    EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  memberId为空
	 * 
	 */
	@Test
	public void frozenTest2(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  会员不存在
	 * 
	 */
	@Test
	public void frozenTest3(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("999999999999");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId为空时，冻结企业，并冻结企业下的所有操作员
	 * 
	 * 会员状态：   1：正常
	 * 操作员1状态：   1：正常
	 * 操作员2状态：   2：冻结
	 * 操作员3状态：   3：注销
	 * 
	 */
	@Test
	public void frozenTest4(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时，验证操作员是否存在
	 * 
	 * 
	 */
	@Test
	public void frozenTest5(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("888");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 操作员状态 == null
	 * 
	 * 
	 */
	@Test
	public void frozenTest6(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("8");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 操作员状态 == 2:冻结
	 * 
	 * 
	 */
	@Test
	public void frozenTest7(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("9");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 操作员状态 == 3:注销
	 * 
	 * 
	 */
	@Test
	public void frozenTest8(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("10");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 操作员状态 == 1:正常
	 * 
	 * 
	 */
	@Test
	public void frozenTest9(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时，校验请求参数memberId是否有效
	 * 
	 * 
	 */
	@Test
	public void frozenTest10(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("3");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  校验operatorId必须为整数
	 * 
	 * 
	 */
	@Test
	public void frozenTest11(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("aa");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：   operatorId为空时，验证会员状态是否为冻结(会员状态 == null)
	 * 
	 * 
	 */
	@Test
	public void frozenTest12(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100003");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：   operatorId为空时，验证会员状态是否为冻结(会员状态 == 2：冻结)
	 * 
	 * 
	 */
	@Test
	public void frozenTest13(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：   operatorId为空时，验证会员状态是否为冻结(会员状态 == 3：注销)
	 * 
	 * 
	 */
	@Test
	public void frozenTest14(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100006");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void unfreezeTest1(){
		EnterpriseStatusUpdateRequest req = null;
	  
	    EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  memberId为空
	 * 
	 */
	@Test
	public void unfreezeTest2(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  校验operatorId必须为整数
	 * 
	 */
	@Test
	public void unfreezeTest3(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("!@a2");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  会员不存在
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest4(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("999999999999");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId为空时，验证会员状态是否为冻结(会员状态 == null)
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest5(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100003");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId为空时，验证会员状态是否为冻结(会员状态 == 1：正常)
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest6(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId为空时，验证会员状态是否为冻结(会员状态 == 3：注销)
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest7(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100006");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId为空时，验证会员状态是否为冻结(会员状态 == 2：冻结)
	 * 
	 * 操作员1状态：   1：正常
	 * 操作员2状态：   2：冻结
	 * 操作员3状态：   3：注销
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest8(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试： operatorId不为空时，验证操作员是否存在
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest9(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("666666");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试： operatorId不为空时，校验请求参数memberId是否有效
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest10(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("3");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId不为空时，操作员状态 == null
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest11(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100003");
		req.setOperatorId("3");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId不为空时
	 * 
	 * 会员状态 == 1：正常
	 * 操作员状态 == 1：正常
	 * 
	 */
	@Test
	public void unfreezeTest12(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员解冻测试：  operatorId不为空时
	 * 
	 * 会员状态 == 1：正常
	 * 操作员状态 == 2：冻结
	 * 
	 */
	@Test
	public void unfreezeTest13(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("9");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 
	 * 会员状态 == 1：正常
	 * 操作员状态 == 3：注销
	 *  
	 */
	@Test
	public void unfreezeTest14(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("10");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时
	 * 
	 * 会员状态 == 2:冻结
	 * 操作员状态 == 2：冻结
	 * 
	 * 
	 */
	@Test
	public void unfreezeTest15(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("11");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	} 
	
	/**
	 * 企业会员注销测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void cancel1(){
		EnterpriseStatusUpdateRequest req = null;
	  
	    EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  memberId为空
	 * 
	 */
	@Test
	public void cancel2(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  校验operatorId必须为整数
	 * 
	 */
	@Test
	public void cancel3(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("!@a2");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  会员不存在
	 * 
	 * 
	 */
	@Test
	public void cancel4(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("999999999999");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId为空时，验证会员状态是否为正常/冻结(会员状态 == null)
	 * 
	 * 
	 */
	@Test
	public void cancel5(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100003");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId为空时，验证会员状态是否为正常/冻结(会员状态 == 1：正常)
	 * 操作员1状态：   1：正常
	 * 操作员2状态：   2：冻结
	 * 操作员3状态：   3：注销	 * 
	 * 
	 */
	@Test
	public void cancel6(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId为空时，验证会员状态是否为正常/冻结(会员状态 == 3：注销)
	 * 
	 * 
	 */
	@Test
	public void cancel7(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100006");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId为空时，验证会员状态是否为正常/冻结(会员状态 == 2：冻结)
	 * 
	 * 操作员1状态：   1：正常
	 * 操作员2状态：   2：冻结
	 * 操作员3状态：   3：注销
	 * 
	 * 
	 */
	@Test
	public void cancel8(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试： operatorId不为空时，验证操作员是否存在
	 * 
	 * 
	 */
	@Test
	public void cancel9(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("666666");
			  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试： operatorId不为空时，校验请求参数memberId是否有效
	 * 
	 * 
	 */
	@Test
	public void cancel10(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("3");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId不为空时，操作员状态 == null
	 * 
	 * 
	 */
	@Test
	public void cancel11(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100003");
		req.setOperatorId("3");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId不为空时，操作员状态 == 1：正常
	 * 
	 * 
	 */
	@Test
	public void cancel12(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员注销测试：  operatorId不为空时，操作员状态 == 2：冻结
	 * 
	 * 
	 */
	@Test
	public void cancel13(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("9");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 企业会员冻结测试：  operatorId不为空时，操作员状态 == 3：注销
	 * 
	 * 
	 */
	@Test
	public void cancel14(){
		EnterpriseStatusUpdateRequest req = new EnterpriseStatusUpdateRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("10");
	  
		EnterpriseStatusUpdateResponse resp = enterpriseUserStatusServiceFacade.enterpriseCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
}
