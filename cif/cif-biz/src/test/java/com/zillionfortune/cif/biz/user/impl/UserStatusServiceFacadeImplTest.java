package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.UserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserStatusServiceFacadeImplTest {

	@Autowired
	UserStatusServiceFacade userStatusServiceFacade;
	
	/**
	 * 个人会员冻结测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void frozenTest1(){
	    UserStatusUpdateRequest req = null;
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试：  memberId为空
	 * 
	 */
	@Test
	public void frozenTest2(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试：  会员不存在
	 * 
	 */
	@Test
	public void frozenTest3(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("888888");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试： status为空
	 * 
	 */
	@Test
	public void frozenTest4(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100002");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试： status为1：正常
	 * 
	 */
	@Test
	public void frozenTest5(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100003");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试： status为2：冻结
	 * 
	 */
	@Test
	public void frozenTest6(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100004");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员冻结测试： status为3：注销
	 * 
	 */
	@Test
	public void frozenTest7(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100005");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userFrozen(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void userUnfreezeTest1(){
	    UserStatusUpdateRequest req = null;
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试：  memberId为空
	 * 
	 */
	@Test
	public void userUnfreezeTest2(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试：  会员不存在
	 * 
	 */
	@Test
	public void userUnfreezeTest3(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("888888");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试： status为空
	 * 
	 */
	@Test
	public void userUnfreezeTest4(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100002");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试： status为1：正常
	 * 
	 */
	@Test
	public void userUnfreezeTest5(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100003");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试： status为2：冻结
	 * 
	 */
	@Test
	public void userUnfreezeTest6(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100004");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员解冻测试： status为3：注销
	 * 
	 */
	@Test
	public void userUnfreezeTest7(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100005");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userUnfreeze(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void userCancelTest1(){
	    UserStatusUpdateRequest req = null;
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试：  memberId为空
	 * 
	 */
	@Test
	public void userCancelTest2(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试：  会员不存在
	 * 
	 */
	@Test
	public void userCancelTest3(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("888888");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试： status为空
	 * 
	 */
	@Test
	public void userCancelTest4(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100002");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试： status为1：正常
	 * 
	 */
	@Test
	public void userCancelTest5(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100003");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试： status为2：冻结
	 * 
	 */
	@Test
	public void userCancelTest6(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100004");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
	
	/**
	 * 个人会员注销测试： status为3：注销
	 * 
	 */
	@Test
	public void userCancelTest7(){
	    UserStatusUpdateRequest req = new UserStatusUpdateRequest();
	    req.setMemberId("100005");
	  
	    UserStatusUpdateResponse resp = userStatusServiceFacade.userCancel(req);
		
		System.out.println(JSON.toJSONString(resp));
		
	}
}
