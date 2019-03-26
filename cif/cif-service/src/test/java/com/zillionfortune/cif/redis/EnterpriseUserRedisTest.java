package com.zillionfortune.cif.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.common.constants.RedisConstants;
import com.zillionfortune.cif.common.constants.RedisKey;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.DateFormat;
import com.zillionfortune.cif.common.util.JsonUtils;
import com.zillionfortune.cif.service.redis.EnterpriseUserRedisService;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;
  
  
/**  
 * 测试 
 * @author kaiyun 
 * @version <b>1.0</b>  
 */     
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseUserRedisTest {  
    
	@Autowired
	EnterpriseUserRedisService enterpriseUserRedisService ; 
      
    /** 
     * 新增 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUser() {  
    	EnterpriseRedisModel user = new EnterpriseRedisModel();  
    	user.setOperatorId("operatorId");
    	user.setMemberId("memberId");
    	user.setUserName("kaiyun");
    	user.setEmail("123@qq.com");
    	user.setMobile("13816947328");
    	user.setLoginSource(1);
    	user.setLoginStatus("1");
    	user.setLoginTime(DateFormat.fullFormat(new Date()));
        boolean result = false;
		try {
			result = enterpriseUserRedisService.add(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
        Assert.assertTrue(result);  
    }  
    
    /** 
     * 批量新增 普通方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers1() {  
        List<EnterpriseRedisModel> list = new ArrayList<EnterpriseRedisModel>();  
        for (int i = 1; i <= 3; i++) {  
        	EnterpriseRedisModel user = new EnterpriseRedisModel();  
        	user.setOperatorId("operatorId" + i);
        	user.setMemberId("memberId");
        	user.setUserName("kaiyun");
        	user.setEmail("123@qq.com");
        	user.setMobile("13816947328");
        	user.setLoginSource(1);
        	user.setLoginStatus("1");
        	user.setLoginTime(DateFormat.fullFormat(new Date()));
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        boolean result = false;
        try {
        	 for (EnterpriseRedisModel user : list) {  
        		 result = enterpriseUserRedisService.add(user);  
             }  
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println(System.currentTimeMillis() -  begin);  
        Assert.assertTrue(result);  
    }  
      
    /** 
     * 批量新增 pipeline方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers2() {  
        List<EnterpriseRedisModel> list = new ArrayList<EnterpriseRedisModel>();  
        for (int i = 1; i <= 3; i++) {  
        	EnterpriseRedisModel user = new EnterpriseRedisModel();  
        	user.setOperatorId("operatorId" + i);
        	user.setMemberId("memberId");
        	user.setUserName("kaiyun");
        	user.setEmail("123@qq.com");
        	user.setMobile("13816947328");
        	user.setLoginSource(1);
        	user.setLoginStatus("1");
        	user.setLoginTime(DateFormat.fullFormat(new Date()));
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        boolean result = false;
		try {
			result = enterpriseUserRedisService.add(list);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
        System.out.println(System.currentTimeMillis() - begin);  
        Assert.assertTrue(result);  
    }  
    
    /** 
     * 校验key是否存在 
     * <br>------------------------------<br> 
     */  
    @Test
    public void testCheck(){
    	String operatorId = "operatorId";
    	boolean result = false;
		try {
			result = enterpriseUserRedisService.check(operatorId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    	Assert.assertTrue(result); 
    }
    
    /** 
     * 获取 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testGetUser() {  
        String id = "operatorId";  
        EnterpriseRedisModel user = null;
		try {
			user = enterpriseUserRedisService.get(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
        Assert.assertNotNull(user);  
        System.out.println(JsonUtils.object2Json(user));
    }  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testUpdate() {  
    	EnterpriseRedisModel user = new EnterpriseRedisModel();  
    	user.setOperatorId("operatorId");
    	user.setMemberId("test_memberId");
    	user.setUserName("kaiyun");
    	user.setEmail("123@qq.com");
    	user.setMobile("13816947328");
    	user.setLoginSource(1);
    	user.setLoginStatus("1");
    	user.setLoginTime(DateFormat.fullFormat(new Date()));
        boolean result = false;
		try {
			result = enterpriseUserRedisService.update(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
        Assert.assertTrue(result);  
    }  
    
    /** 
     * 设置键的有效时期（秒） 
     * <br>------------------------------<br> 
     */  
    @Test
    public void testExpire(){
    	String operatorId = "operatorId";
    	long seconds = RedisConstants.EXPIRE_SECOND_DEFAULT;
    	boolean result = false;
		try {
			result = enterpriseUserRedisService.expire(operatorId, seconds);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    	Assert.assertTrue(result); 
    }
      
    /** 
     * 通过key删除单个 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDelete() {  
        String key = "operatorId";  
        try {
			enterpriseUserRedisService.delete(key);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
    }  
      
    /** 
     * 批量删除 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDeletes() {  
        List<String> list = new ArrayList<String>();  
        for (int i = 1; i <= 3; i++) {  
            list.add(RedisKey.getEnterpriseLoginInfoKey("operatorId") + i);  
        }  
        try {
			enterpriseUserRedisService.delete(list);
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
    }  
      
    /** 
     * 设置userDao 
     * @param userDao the userDao to set 
     */  
    public void setUserDao(EnterpriseUserRedisService enterpriseUserRedisService) {  
        this.enterpriseUserRedisService = enterpriseUserRedisService;  
    }  
}  