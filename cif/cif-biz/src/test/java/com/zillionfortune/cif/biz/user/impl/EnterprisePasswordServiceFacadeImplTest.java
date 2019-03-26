package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.facade.user.EnterprisePasswordServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterprisePasswordServiceFacadeImplTest {

	@Autowired
	EnterprisePasswordServiceFacade enterprisePasswordServiceFacade;
	
	/**
	 * 企业会员重置登录密码测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest1(){
		EnterpriseLoginPasswordRetrieveRequest req = null;
	  
		EnterpriseLoginPasswordRetrieveResponse resp = enterprisePasswordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员重置登录密码测试：  status = 1：正常
	 * 
	 */
	@Test
	public void retrieveLoginPasswordTest2(){
		EnterpriseLoginPasswordRetrieveRequest req = new EnterpriseLoginPasswordRetrieveRequest();
		req.setCustomerNo("100004");
		req.setUserName("wzn");
		req.setNewPassword("aabb54a");	
	  
		EnterpriseLoginPasswordRetrieveResponse resp = enterprisePasswordServiceFacade.retrieveLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  请求参数对象为空
	 * 
	 */
	@Test
	public void modifyPasswordTest1(){
		EnterpriseLoginPasswordModifyRequest req = null;
	  
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  memberId字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest2(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setOperatorId("3");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
	  
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  operatorId字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest3(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100003");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
	  
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  orgiPassword字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest4(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setOperatorId("3");
		req.setMemberId("100001100003");
		req.setNewPassword("aaa55ccc");
	  
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  newPassword字段为空
	 * 
	 */
	@Test
	public void modifyPasswordTest5(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setOperatorId("3");
		req.setMemberId("100001100003");
		req.setOrgiPassword("wwwwww1");
	  
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  memberId,operatorId,orgiPassword,newPassword字段皆为空
	 * 
	 */
	@Test
	public void modifyPasswordTest6(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  会员不存在（根据operatorId在enterprise_operator表中，查询不到数据）
	 * 
	 */
	@Test
	public void modifyPasswordTest7(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100003");
		req.setOperatorId("88888");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  【请求参数operatorId对应的memberId】和【请求参数memberId】不一致
	 * 
	 */
	@Test
	public void modifyPasswordTest8(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("888888888888");
		req.setOperatorId("3");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  会员不存在（根据enterprise_operator表中的memberId在enterprise_member表中，查询不到数据）
	 * 
	 */
	@Test
	public void modifyPasswordTes9(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001999999");
		req.setOperatorId("4");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  会员状态status == null
	 * 
	 */
	@Test
	public void modifyPasswordTes10(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100003");
		req.setOperatorId("3");
		req.setOrgiPassword("wwwwww1");
		req.setNewPassword("aaa55ccc");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：
	 * 会员状态status == 1:正常 
	 * 操作员status == 1:正常
	 * 原密码（orgiPassword）不正确
	 * 
	 */
	@Test
	public void modifyPasswordTes11(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
		req.setOrgiPassword("xxxxxxxx");
		req.setNewPassword("aaa55ccc");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  
	 * 会员状态status == 1:正常 
	 * 操作员status == 1:正常
	 * 新密码（newPassword）和原密码（orgiPassword）重复
	 * 
	 */
	@Test
	public void modifyPasswordTes12(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("wwwwww3");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  
	 * 会员状态status == 1:正常 
	 * 操作员status == 1:正常
	 * 原密码正确
	 * 新密码（newPassword）和原密码（orgiPassword）不重复
	 * 
	 */
	@Test
	public void modifyPasswordTes13(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("5");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  
	 * 会员状态status == 2:冻结
	 * 
	 */
	@Test
	public void modifyPasswordTes14(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100005");
		req.setOperatorId("6");
		req.setOrgiPassword("wwwwww4");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  
	 * 会员状态status == 3:注销
	 * 
	 */
	@Test
	public void modifyPasswordTes15(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100006");
		req.setOperatorId("7");
		req.setOrgiPassword("wwwwww5");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：
	 * 会员状态status == 1:正常 
	 * 操作员status == null
	 * 
	 */
	@Test
	public void modifyPasswordTes16(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("8");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：
	 * 会员状态status == 1:正常 
	 * 操作员status == 2:冻结
	 * 
	 */
	@Test
	public void modifyPasswordTes17(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("9");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：
	 * 会员状态status == 1:正常 
	 * 操作员status == 3:注销
	 * 
	 */
	@Test
	public void modifyPasswordTes18(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("10");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * 企业会员更新登录密码测试：  校验operatorId必须位整数
	 * 
	 */
	@Test
	public void modifyPasswordTes19(){
		EnterpriseLoginPasswordModifyRequest req = new EnterpriseLoginPasswordModifyRequest();
		req.setMemberId("100001100004");
		req.setOperatorId("aa");
		req.setOrgiPassword("wwwwww3");
		req.setNewPassword("aaa66ddd");
		
		EnterpriseLoginPasswordModifyResponse resp = enterprisePasswordServiceFacade.modifyLoginPassword(req);
		
		System.out.println(JSON.toJSONString(resp));
	}

	
	/**
	 * retrieveTradePasswordTest:企业会员重置交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void retrieveTradePasswordTest(){
		
		System.out.println(JSON.toJSON(enterprisePasswordServiceFacade.retrieveTradePassword("E201612011807593243515552", "XXXOOO")));
		
	}
	
	/**
	 * retrieveTradePasswordTest:企业会员更新交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void modifyTradePasswordTest(){ 
		
		System.out.println(JSON.toJSON(enterprisePasswordServiceFacade.modifyTradePassword("E201612011807593243515552", "Psasrr", "XXXOOO")));
		
	}
	
	/**
	 * retrieveTradePasswordTest:企业会员验证交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void verifyTradePasswordTest(){
		
		System.out.println(JSON.toJSON(enterprisePasswordServiceFacade.verifyTradePassword("E201612011807593243515552", "Psasrr")));
		
	}
	
	/**
	 * setTradePasswordTest:企业会员设置交易密码测试. <br/>
	 *
	 * @param 
	 * @return 
	 * @throws
	 */
	@Test
	public void setTradePasswordTest(){
		
		System.out.println(JSON.toJSON(enterprisePasswordServiceFacade.setTradePassword("E201612011807593243515552", "zhengrunlongyyy")));
		
	}
	
}
