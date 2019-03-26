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
import com.zillionfortune.cif.service.redis.EnterpriseTokenManager;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;

/**
 * ClassName: RedisTokenManagerEnterpriseTest <br/>
 * Function: 企业_通过Redis存储和验证token的实现类_测试. <br/>
 * Date: 2016年11月21日 下午4:43:44 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseTokenManagerTest {
	
	@Autowired
	EnterpriseTokenManager redis;
	
//	@Test  
	public void testCreateToken(){
		EnterpriseTokenModel tokenModel = null;
		try {
			tokenModel = redis.createToken("1111");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------testCreateToken.resp = " + "\t" + tokenModel.getOperatorId() + "\t" + tokenModel.getToken() );
	}
	
//	@Test
	public void tesCheckToken(){
		String operatorId = "1111";//key
		String token;
		boolean result;
		try {
			token = redis.getTokenByKey(operatorId);//value
			EnterpriseTokenModel model = new EnterpriseTokenModel(operatorId,token);
			result = redis.checkToken(model);
			Assert.assertTrue(result); 
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
	}
	
//	@Test
	public void testTokenExpire(){
		String operatorId = "1111";//key
		try {
			boolean result = redis.expire(operatorId, RedisConstants.EXPIRE_SECOND_DEFAULT);
			Assert.assertTrue(result);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteToken(){
		String memberId = "1111";
		try {
			long num = redis.deleteToken(memberId);
			System.out.println("-------------------testDeleteToken.resp = " + "\t" + num);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	

}
