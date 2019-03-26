package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.PasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class PasswordServiceFacadeImplTest {

	@Autowired
	PasswordServiceFacade passwordServiceFacade;
	
	/**
	 * 个人会员重置登录密码测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest1(){
		IndividualLoginPasswordRetrieveRequest req = null;
	  
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  memberId字段为空
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest2(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setNewPassword("wwwwww1");	
	  
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  newPassword字段为空
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest3(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("100001100003");
	  
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  memberId，newPassword字段皆为空
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest4(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  会员不存在
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest5(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("888888");
		req.setNewPassword("wwwwww3");
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  会员状态status == null
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest6(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("100002");
		req.setNewPassword("wwwwww1");
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  会员状态status == 1:正常
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest7(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("100003");
		req.setNewPassword("XXX22yyy");
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  会员状态status == 2:冻结
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest8(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("100004");
		req.setNewPassword("aaa22bbb");
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员重置登录密码测试：  会员状态status == 3:注销
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest9(){
		IndividualLoginPasswordRetrieveRequest req = new IndividualLoginPasswordRetrieveRequest();
		req.setMemberId("100005");
		req.setNewPassword("aaa22bbb");
		IndividualLoginPasswordRetrieveResponse resp = passwordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void modifyPasswordTest1(){
		IndividualLoginPasswordModifyRequest req = null;
	  
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  memberId字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest2(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
	  
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  operatorId字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest3(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100001100003");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
	  
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}

	/**
	 * 个人会员更新登录密码测试：  newPassword字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest4(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100001100003");
		req.setOrgiPassword("wwwwww1");
	  
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  memberId,orgiPassword,newPassword字段皆为空
	 * 
	 */
	@Test
	public void modifyPasswordTest5(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  会员不存在
	 * 
	 */
	@Test
	public void modifyPasswordTes6(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("888888");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  会员状态status == null
	 * 
	 */
	@Test
	public void modifyPasswordTes7(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100002");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  会员状态status == 1:正常     原密码（orgiPassword）不正确
	 * 
	 */
	@Test
	public void modifyPasswordTes8(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100003");
		req.setOrgiPassword("xxxxxxxx");
		req.setNewPassword("aaa55ccc");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  
	 * 会员状态status == 1:正常
	 * 新密码（newPassword）和原密码（orgiPassword）重复
	 * 
	 */
	@Test
	public void modifyPasswordTes9(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100003");
		req.setOrgiPassword("wwwwww2");
		req.setNewPassword("wwwwww2");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  
	 * 会员状态status == 1:正常
	 * 原密码正确
	 * 新密码（newPassword）和原密码（orgiPassword）不重复
	 * 
	 */
	@Test
	public void modifyPasswordTes10(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100003");
		req.setOrgiPassword("wwwwww2");
		req.setNewPassword("aaa66ddd");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  
	 * 会员状态status == 2:冻结
	 * 
	 */
	@Test
	public void modifyPasswordTes11(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100004");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 个人会员更新登录密码测试：  
	 * 会员状态status == 3:注销
	 * 
	 */
	@Test
	public void modifyPasswordTes12(){
		IndividualLoginPasswordModifyRequest req = new IndividualLoginPasswordModifyRequest();
		req.setMemberId("100005");
		req.setOrgiPassword("wwwwww4");
		req.setNewPassword("aaa66ddd");
		
		IndividualLoginPasswordModifyResponse resp = passwordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * setTradePasswordTest:个人会员设置交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void setTradePasswordTest(){
		
		System.out.println(JSON.toJSON(passwordServiceFacade.setTradePassword("PM201612091449294864257267", "password")));
		
	}
	
	/**
	 * retrieveTradePasswordTest:个人会员重置交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void retrieveTradePasswordTest(){
		
		System.out.println(JSON.toJSON(passwordServiceFacade.retrieveTradePassword("I201611301619518712796502", "xxxoo786")));
		
	}
	
	/**
	 * retrieveTradePasswordTest:个人会员更新交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void modifyTradePasswordTest(){ 
		
		System.out.println(JSON.toJSON(passwordServiceFacade.modifyTradePassword("PM201612091449294864257267", "nihao", "password")));
		
	}
	
	/**
	 * retrieveTradePasswordTest:个人会员验证交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void verifyTradePasswordTest(){
		
		System.out.println(JSON.toJSON(passwordServiceFacade.verifyTradePassword("PM201612091449294864257267", "nihao")));
		
	}
	
	
}
