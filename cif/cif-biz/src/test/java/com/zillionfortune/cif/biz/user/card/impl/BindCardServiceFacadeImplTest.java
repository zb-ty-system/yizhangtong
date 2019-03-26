package com.zillionfortune.cif.biz.user.card.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.card.BindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class BindCardServiceFacadeImplTest {
	@Autowired
	private BindCardServiceFacade facade;
	
	@Test
	public void bindCardTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.bindCard(null)));
	}
	
	@Test
	public void bindCardTestParamIsEmpty() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("1234");
		req.setCertificateType(1);
		req.setCertificateNo("341124198603202818");
		req.setBankcardNo("6217001210069790457");
		req.setRealName("pengting");
		//req.setMobile("15800756329");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestParamFormatError() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("1234");
		req.setCertificateType(1);
		req.setCertificateNo("341124198603202818");
		req.setBankcardNo("6217001210069790457");
		req.setRealName("111");
		req.setMobile("15800756329");
		req.setBankCardType("a");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestMemberNotFound() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("1234_no");
		req.setCertificateType(1);
		req.setCertificateNo("341124198603202818");
		req.setBankcardNo("6217001210069790457");
		req.setRealName("111");
		req.setMobile("15800756329");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestMemeberIsUnnomarl() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("1234");
		req.setCertificateType(1);
		req.setCertificateNo("341124198603202818");
		req.setBankcardNo("6217001210069790457");
		req.setRealName("111");
		req.setMobile("15800756329");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestAuthFail() {
		// {"realName":["111"],"certificateNo":["341124198603202819"],"mobile":["15800756329"],"bankcardNo":["6217001210069790457"],"bankCode":["CCB"],"cardType":["D"],"memberId":["1234"]}
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791110");
		req.setRealName("p1t");
		req.setMobile("18621197591");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTest() {
		// {"realName":["111"],"certificateNo":["341124198603202819"],"mobile":["15800756329"],"bankcardNo":["6217001210069790457"],"bankCode":["CCB"],"cardType":["D"],"memberId":["1234"]}
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("I201612051639175666344272");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069799889");
		req.setRealName("李小明");
		req.setMobile("18988888889");
		req.setBankCardType("D");
		req.setBankCode("ICBC");
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void bindCardTestBindCardExist() {
		// {"realName":["111"],"certificateNo":["341124198603202819"],"mobile":["15800756329"],"bankcardNo":["6217001210069790457"],"bankCode":["CCB"],"cardType":["D"],"memberId":["1234"]}
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791115");
		req.setRealName("pt");
		req.setMobile("18621197591");
		
		System.out.println(JSONObject.toJSONString(facade.bindCard(req)));
	}
	
	@Test
	public void smsVerificationTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.smsVerification(null)));
	}
	
	@Test
	public void smsVerificationTestParamIsEmpty() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791115");
		req.setRealName("pt");
		req.setMobile("18621197591");
		/*req.setSmsCode("smsCode");*/
		
		System.out.println(JSONObject.toJSONString(facade.smsVerification(req)));
	}
	
	@Test
	public void smsVerificationTestFormatError() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(21);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791115");
		req.setRealName("pt");
		req.setMobile("18621197591");
		req.setSmsCode("smsCode");
		
		System.out.println(JSONObject.toJSONString(facade.smsVerification(req)));
	}
	
	@Test
	public void smsVerificationTestMemberIdNotFound() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001——");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791115");
		req.setRealName("pt");
		req.setMobile("18621197591");
		req.setSmsCode("smsCode");
		
		System.out.println(JSONObject.toJSONString(facade.smsVerification(req)));
	}
	
	@Test
	public void smsVerificationTestBindCardExist() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069791115");
		req.setRealName("pt");
		req.setMobile("18621197591");
		req.setSmsCode("smsCode");
		
		System.out.println(JSONObject.toJSONString(facade.smsVerification(req)));
	}
	
	@Test
	public void smsVerificationTest() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("I201612051639175666344272");
		req.setCertificateType(1);
		req.setCertificateNo("430381199001222099");
		req.setBankcardNo("6217001210069799889");
		req.setRealName("李小明");
		req.setMobile("18988888889");
		req.setSmsCode("smsCode");
		
		System.out.println(JSONObject.toJSONString(facade.smsVerification(req)));
	}
	
	@Test
	public void unBindCardTestParamIsNull() {
		System.out.println(JSONObject.toJSONString(facade.unBindCard(null)));
	}
	
	@Test
	public void unBindCardTestParamIsEmpty() {
		BindCardRequest req = new BindCardRequest();
		req.setBankcardNo("6217001210069795555");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestMemberNotFound() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001——");
		req.setBankcardNo("6217001210069791113");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestMemberStatusIsUnnomarl() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("1234");
		req.setBankcardNo("6217001210069791113");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTestBindCardNotFound() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("100001100001");
		req.setBankcardNo("6217001210069791113");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void unBindCardTest() {
		BindCardRequest req = new BindCardRequest();
		req.setMemberId("I201612051639175666344272");
		req.setBankcardNo("6217001210069799889");
		System.out.println(JSONObject.toJSONString(facade.unBindCard(req)));
	}
	
	@Test
	public void queryBindCardTestParamIsNull() {
		BindCardQueryRequest req = new BindCardQueryRequest();
		req.setMemberId("100001100001");
		System.out.println(JSONObject.toJSONString(facade.queryBindCard(null)));
	}
	
	@Test
	public void queryBindCardTestParamIsEmpty() {
		BindCardQueryRequest req = new BindCardQueryRequest();
		System.out.println(JSONObject.toJSONString(facade.queryBindCard(req)));
	}
	
	@Test
	public void queryBindCardTestMemeberNotFound() {
		BindCardQueryRequest req = new BindCardQueryRequest();
		req.setMemberId("100001100001_");
		System.out.println(JSONObject.toJSONString(facade.queryBindCard(req)));
	}
	
	@Test
	public void queryBindCardTestParamFormatError() {
		BindCardQueryRequest req = new BindCardQueryRequest();
		req.setMemberId("100001100001");
		req.setCertificateType(12);
		System.out.println(JSONObject.toJSONString(facade.queryBindCard(req)));
	}
	
	@Test
	public void queryBindCardTest() {
		BindCardQueryRequest req = new BindCardQueryRequest();
		req.setMemberId("I201612051639175666344272");
		System.out.println(JSONObject.toJSONString(facade.queryBindCard(req)));
	}
}

