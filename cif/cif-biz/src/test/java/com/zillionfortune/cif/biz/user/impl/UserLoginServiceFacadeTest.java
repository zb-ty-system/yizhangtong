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

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.UserLoginServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutResponse;
import com.zillionfortune.cif.service.redis.TokenManager;
import com.zillionfortune.cif.service.user.PersonMemberService;

/**
 * ClassName: UserLoginServiceFacadeTest <br/>
 * Function: 个人_用户登入鉴权_单元测试类. <br/>
 * Date: 2016年11月23日 上午11:16:58 <br/>
 *
 * @author kaiyun
 * @version
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserLoginServiceFacadeTest {

	@Autowired
	UserLoginServiceFacade userLoginServiceFacade;

	@Autowired
	PersonMemberService personMemberService;
	
	@Autowired
	TokenManager tokenManager;

	/**
	 * test_auth1:登入_正常. <br/>
	 *
	 */
	@Test
	public void test_login1() {
		// 根据memberId，查个人会员表
		String memberId = "PM201612141811235690129744";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String userName = personMember.getUserName();// 用户名，必输
		String password = personMember.getPassword();// 登入密码
		String loginSource = "1";// 登入来源：1pc 2Android 3ios，必输
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(userName, password, loginSource));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_用户名不能为空. <br/>
	 *
	 */
	@Test
	public void test_login2() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String userName = "";// 用户名，必输
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(userName, personMember.getPassword(), "1"));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_用户名错误. <br/>
	 *
	 */
	@Test
	public void test_login21() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String userName = "error";// 用户名，必输
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(userName, personMember.getPassword(), "1"));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_若密码为空时，正常授权 <br/>
	 *
	 */
	@Test
	public void test_login3() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String password = null;
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(personMember.getUserName(), password, "1"));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_若密码不为空时，密码输入错误. <br/>
	 *
	 */
	@Test
	public void test_login31() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String password = "error";
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(personMember.getUserName(), password, "1"));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_登入来源不存在. <br/>
	 *
	 */
	@Test
	public void test_login4() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String loginResouce = "5";
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(personMember.getUserName(), personMember.getPassword(), loginResouce));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_auth1:登入_登入来源只支持数字. <br/>
	 *
	 */
	@Test
	public void test_login41() {
		// 根据memberId，查个人会员表
		String memberId = "100006";
		PersonMember personMember = personMemberService
				.queryByMemberId(memberId);
		// 封装请求参数
		String loginResouce = "z";
		// 执行
		IndividualLoginResponse resp = userLoginServiceFacade
				.login(new IndividualLoginRequest(personMember.getUserName(), personMember.getPassword(), loginResouce));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	

	/**
	 * test_login1:登入鉴权_正常登入. <br/>
	 *
	 */
	@Test
	public void test_auth1() {
		String memberId = "100006";
		try {
			//根据memberId，查对应的accessToken
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginAuthResponse resp = userLoginServiceFacade.auth(new IndividualLoginAuthRequest(memberId,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * test_login1:登入鉴权_memberId不能为空. <br/>
	 *
	 */
	@Test
	public void test_auth2() {
		String memberId = "";
		try {
			//根据memberId，查对应的accessToken
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginAuthResponse resp = userLoginServiceFacade.auth(new IndividualLoginAuthRequest(memberId,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * test_login1:登入鉴权_memberId不存在. <br/>
	 *
	 */
	@Test
	public void test_auth21() {
		String memberId = "100006";
		String memberId_error = "error";
		try {
			//根据memberId，查对应的accessToken
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginAuthResponse resp = userLoginServiceFacade.auth(new IndividualLoginAuthRequest(memberId_error,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_login1:登入鉴权_token不能为空. <br/>
	 *
	 */
	@Test
	public void test_auth3() {
		String memberId = "100006";
		String accessToken = "";
		// 执行
		IndividualLoginAuthResponse resp = userLoginServiceFacade.auth(new IndividualLoginAuthRequest(memberId,accessToken));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_login1:登入鉴权_token错误. <br/>
	 *
	 */
	@Test
	public void test_auth31() {
		String memberId = "100006";
		String accessToken = "error";
		// 执行
		IndividualLoginAuthResponse resp = userLoginServiceFacade.auth(new IndividualLoginAuthRequest(memberId,accessToken));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}

	
	/**
	 * test_loginout:登出_正常登出. <br/>
	 *
	 */
	@Test
	public void test_loginout1() {
		String memberId = "100006";
		try {
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginOutResponse resp = userLoginServiceFacade.loginout(new IndividualLoginOutRequest(memberId,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		
	}
	
	/**
	 * test_loginout:登出_memberId不能为空. <br/>
	 *
	 */
	@Test
	public void test_loginout2() {
		String memberId = "";
		try {
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginOutResponse resp = userLoginServiceFacade.loginout(new IndividualLoginOutRequest(memberId,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout:登出_memberId不存在. <br/>
	 *
	 */
	@Test
	public void test_loginout21() {
		String memberId_error = "error";
		String memberId = "100006";
		try {
			String accessToken = tokenManager.getTokenByKey(memberId);
			// 执行
			IndividualLoginOutResponse resp = userLoginServiceFacade.loginout(new IndividualLoginOutRequest(memberId_error,accessToken));
			// 输出
			System.out.println(JSON.toJSONString(resp));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test_loginout:登出_token不能为空. <br/>
	 *
	 */
	@Test
	public void test_loginout3() {
		String memberId = "100006";
		String accessToken = "";

		// 执行
		IndividualLoginOutResponse resp = userLoginServiceFacade.loginout(new IndividualLoginOutRequest(memberId,accessToken));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}
	
	/**
	 * test_loginout:登出_token错误. <br/>
	 *
	 */
	@Test
	public void test_loginout31() {
		String memberId = "100006";
		String accessToken = "error";

		// 执行
		IndividualLoginOutResponse resp = userLoginServiceFacade.loginout(new IndividualLoginOutRequest(memberId,accessToken));
		// 输出
		System.out.println(JSON.toJSONString(resp));
	}

}
