package com.zillionfortune.cif.service.redis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zillionfortune.cif.common.constants.RedisKey;
import com.zillionfortune.cif.common.constants.SystemParameterConstants;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.JsonUtils;
import com.zillionfortune.cif.dal.entity.ParameterConfig;
import com.zillionfortune.cif.service.ParameterConfigService;
import com.zillionfortune.cif.service.redis.AbstractBaseRedisService;
import com.zillionfortune.cif.service.redis.UserRedisService;
import com.zillionfortune.cif.service.redis.model.IndividualRedisModel;
  
  
/**
 * ClassName: UserRedisServiceImpl <br/>
 * Function: redis_个人用户信息service操作_接口实现. <br/>
 * Date: 2016年12月1日 下午3:44:30 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Service
public class UserRedisServiceImpl extends AbstractBaseRedisService<String, IndividualRedisModel> implements UserRedisService {  
	
    /**
     * 新增 .
     * @see com.zillionfortune.cif.service.redis.UserRedisService#add(com.zillionfortune.cif.service.redis.model.IndividualRedisModel)
     */
    public boolean add(final IndividualRedisModel individualRedisModel) throws BusinessException {  
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_DEFAULT+"],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginInfoKey(individualRedisModel.getMemberId()));  
                byte[] name = serializer.serialize(JsonUtils.object2Json(individualRedisModel));  
                boolean bl = connection.setNX(key, name); 
                if(bl){//设置键的有效时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()) );
                }
                return bl; 
            }  
        });  
        return result;  
    }  
    
    /**
     * 批量新增 使用pipeline方式   .
     * @see com.zillionfortune.cif.service.redis.UserRedisService#add(java.util.List)
     */
    public boolean add(final List<IndividualRedisModel> individualRedisModelList) throws BusinessException {  
        Assert.notEmpty(individualRedisModelList);  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                for (IndividualRedisModel user : individualRedisModelList) {  
                    byte[] key  = serializer.serialize(RedisKey.getIndividualLoginInfoKey(user.getMemberId()));  
                    byte[] name = serializer.serialize(JsonUtils.object2Json(user));  
                    connection.setNX(key, name);  
                }  
                return true;  
            }  
        }, false, true);  
        return result;  
    }  
      
    /**
     * 删除.
     * @see com.zillionfortune.cif.service.redis.UserRedisService#delete(java.lang.String)
     */
    public void delete(String memberId) throws BusinessException {  
        List<String> list = new ArrayList<String>();  
        list.add(RedisKey.getIndividualLoginInfoKey(memberId));  
        delete(list);  
    }  
  
    /**
     * 删除多个 .
     * @see com.zillionfortune.cif.service.redis.UserRedisService#delete(java.util.List)
     */
    public void delete(List<String> memberIdList) throws BusinessException {  
        redisTemplate.delete(memberIdList);  
    }  
  
    /**
     * 修改.
     * @see com.zillionfortune.cif.service.redis.UserRedisService#update(com.zillionfortune.cif.service.redis.model.IndividualRedisModel)
     */
    public boolean update(final IndividualRedisModel individualRedisModel) throws BusinessException {  
    	//校验参数
        String key = individualRedisModel.getMemberId();  
        if (get(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        }  
        
        //根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_DEFAULT+"],查找不到参数表[ParameterConfig]记录！");
    	}
        
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginInfoKey(individualRedisModel.getMemberId()));  
                byte[] name = serializer.serialize(JsonUtils.object2Json(individualRedisModel));  
                connection.set(key, name); 
                //设置键的有效时间
                connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()) );
                return true;  
            }  
        });  
        return result;  
    }  
  
    /**
     * 通过key获取.
     * @see com.zillionfortune.cif.service.redis.UserRedisService#get(java.lang.String)
     */
    public IndividualRedisModel get(final String memberId) throws BusinessException {  
    	IndividualRedisModel result = redisTemplate.execute(new RedisCallback<IndividualRedisModel>() {  
            public IndividualRedisModel doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getIndividualLoginInfoKey(memberId));  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String ObjStr = serializer.deserialize(value);  
                IndividualRedisModel user = JsonUtils.json2Object(ObjStr, IndividualRedisModel.class);
                return user; 
            }  
        });  
        return result;  
    }  
    
    /**
     * 设置过期时间 （秒）.
     * @see com.zillionfortune.cif.service.redis.UserRedisService#expire(java.lang.String, long)
     */
    public boolean expire(final String memberId, final long seconds) throws BusinessException {  
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getIndividualLoginInfoKey(memberId));  
                return connection.expire(key, seconds);
            }  
        });  
        return result;  
    }
    
    /**
     * 校验key是否存在.
     * @see com.zillionfortune.cif.service.redis.UserRedisService#check(java.lang.String)
     */
	public boolean check(final String memberId) throws BusinessException {
		//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_USERINFO_EXPRISE_TIME_UPDATE+"],查找不到参数表[ParameterConfig]记录！");
    	}
    	
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getIndividualLoginInfoKey(memberId)); 
                byte[] value = connection.get(key);
                if (value == null) {  
                    return false;  
                } 
                //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
            	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                return true;
            }  
        });  
        return result;  
	}
}  
