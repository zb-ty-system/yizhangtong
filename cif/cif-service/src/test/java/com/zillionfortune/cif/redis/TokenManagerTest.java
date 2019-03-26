package com.zillionfortune.cif.redis;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.common.constants.RedisConstants;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.service.redis.TokenManager;
import com.zillionfortune.cif.service.redis.model.TokenModel;

/**
 * ClassName: RedisTokenManagerTest <br/>
 * Function: 个人_通过Redis存储和验证token的实现类_测试. <br/>
 * Date: 2016年11月21日 下午4:43:44 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class TokenManagerTest {
	
	@Autowired
	TokenManager redis;
	
	@Test  
	public void testCreateToken(){
		TokenModel tokenModel = null;
		try {
			tokenModel = redis.createToken("1111");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------testCreateToken.resp = " + "\n" + tokenModel.getMemberId() + "\n" + tokenModel.getToken() );
	}
	
	@Test
	public void testGetTokenByKey(){
		String memberId = null;
		String token = null;
		memberId = "1111";//
		
		try {
			token = redis.getTokenByKey(memberId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------testGetTokenByKey.resp = " + "\n" + token );
	}
	
	@Test
	public void testCheckToken(){
		String memberId = "1111";//key
		String token;
		try {
			token = redis.getTokenByKey(memberId);
			boolean result = redis.checkToken(new TokenModel(memberId,token));
			Assert.assertTrue(result);  
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTokenExpire(){
		String memberId = "1111";//key
		try {
			boolean result = redis.expire(memberId, RedisConstants.EXPIRE_SECOND_DEFAULT);
			Assert.assertTrue(result);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteToken(){
		String memberId = "1111";
		try {
			redis.deleteToken(memberId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	

}
