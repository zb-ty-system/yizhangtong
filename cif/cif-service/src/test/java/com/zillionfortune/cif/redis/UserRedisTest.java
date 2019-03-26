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
import com.zillionfortune.cif.service.redis.UserRedisService;
import com.zillionfortune.cif.service.redis.model.IndividualRedisModel;
  
  
/**  
 * 测试 
 * @author kaiyun 
 * @version <b>1.0</b>  
 */     
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class UserRedisTest {  
    
	@Autowired
    UserRedisService userRedisService ; 
      
    /** 
     * 新增 
     * <br>------------------------------<br> 
     * @throws BusinessException 
     */  
    @Test  
    public void testAddUser() throws BusinessException {  
    	IndividualRedisModel user = new IndividualRedisModel();  
    	user.setMemberId("memberId");
    	user.setUserName("test");
    	user.setPhoneNo("13816947328");
    	user.setEmail("123@qq.com");
    	user.setLoginSource(1);
    	user.setLoginStatus("1");
    	user.setLoginTime(DateFormat.fullFormat(new Date()));
        boolean result = userRedisService.add(user);  
        Assert.assertTrue(result);  
    }  
      
    /** 
     * 批量新增 普通方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers() throws BusinessException {  
        List<IndividualRedisModel> list = new ArrayList<IndividualRedisModel>();  
        for (int i = 1; i <= 3; i++) {  
        	IndividualRedisModel user = new IndividualRedisModel();  
        	user.setMemberId("memberId" + i);
        	user.setUserName("test" + i);
        	user.setPhoneNo("13816947328");
        	user.setEmail("123@qq.com");
        	user.setLoginSource(1);
        	user.setLoginStatus("1");
        	user.setLoginTime(DateFormat.fullFormat(new Date()));
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        for (IndividualRedisModel user : list) {  
        	userRedisService.add(user);  
        }  
        System.out.println(System.currentTimeMillis() -  begin);  
    }  
    
    /** 
     * 批量新增 pipeline方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers2() throws BusinessException {  
        List<IndividualRedisModel> list = new ArrayList<IndividualRedisModel>();  
        for (int i = 4; i <= 6; i++) {  
        	IndividualRedisModel user = new IndividualRedisModel();  
        	user.setMemberId("memberId" + i);
        	user.setUserName("test" + i);
        	user.setPhoneNo("13816947328");
        	user.setEmail("123@qq.com");
        	user.setLoginSource(1);
        	user.setLoginStatus("1");
        	user.setLoginTime(DateFormat.fullFormat(new Date())); 
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        boolean result = userRedisService.add(list);  
        System.out.println(System.currentTimeMillis() - begin);  
        Assert.assertTrue(result);  
    }  
    
    /** 
     * 校验key是否存在 
     * <br>------------------------------<br> 
     */  
    @Test
    public void testCheck() throws BusinessException{
    	String memberId = "memberId";
    	boolean result = userRedisService.check(memberId);
    	Assert.assertTrue(result); 
    }
    
    /** 
     * 获取 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testGetUser() throws BusinessException {  
        String id = "memberId";  
        IndividualRedisModel user = userRedisService.get(id);  
        Assert.assertNotNull(user);  
        System.out.println(JsonUtils.object2Json(user));
    }  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testUpdate() throws BusinessException {  
    	IndividualRedisModel user = new IndividualRedisModel();  
    	user.setMemberId("memberId");
    	user.setUserName("test_update");
    	user.setPhoneNo("13816947328");
    	user.setEmail("123@qq.com");
    	user.setLoginSource(1);
    	user.setLoginStatus("1");
    	user.setLoginTime(DateFormat.fullFormat(new Date())); 
        boolean result = userRedisService.update(user);  
        Assert.assertTrue(result);  
    }  
    
    /** 
     * 设置键的有效时期（秒） 
     * <br>------------------------------<br> 
     */  
    @Test
    public void testExpire() throws BusinessException {
    	String memberId = "memberId";
    	long seconds = RedisConstants.EXPIRE_SECOND_DEFAULT;
    	boolean result = userRedisService.expire(memberId, seconds);
    	Assert.assertTrue(result); 
    }
      
    /** 
     * 通过key删除单个 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDelete() throws BusinessException {  
        String key = "memberId";  
        userRedisService.delete(key);  
    }  
      
    /** 
     * 批量删除 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDeletes() throws BusinessException {  
        List<String> list = new ArrayList<String>();  
        for (int i = 1; i <= 3; i++) {  
            list.add(RedisKey.getIndividualLoginInfoKey("memberId") + i);  
        }  
        userRedisService.delete(list);  
    }  
      
    /** 
     * 设置userDao 
     * @param userDao the userDao to set 
     */  
    public void setUserDao(UserRedisService userRedisService) {  
        this.userRedisService = userRedisService;  
    }  
}  