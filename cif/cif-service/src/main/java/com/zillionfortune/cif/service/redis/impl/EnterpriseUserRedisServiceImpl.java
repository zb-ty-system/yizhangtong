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
import com.zillionfortune.cif.service.redis.EnterpriseUserRedisService;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;
  
  
/**
 * ClassName: EnterpriseUserRedisServiceImpl <br/>
 * Function: redis_企业用户信息service操作_接口实现. <br/>
 * Date: 2016年12月1日 下午3:45:47 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Service
public class EnterpriseUserRedisServiceImpl extends AbstractBaseRedisService<String, EnterpriseRedisModel> implements EnterpriseUserRedisService {  
	
	/**
	 * 新增.
	 * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#add(com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel)
	 */
	@Override
    public boolean add(final EnterpriseRedisModel enterpriseRedisModel) throws BusinessException {  
		//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_DEFAULT + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(enterpriseRedisModel.getOperatorId()));  
                byte[] name = serializer.serialize(JsonUtils.object2Json(enterpriseRedisModel));  
                boolean bl = connection.setNX(key, name); 
                if(bl){//设置键的有效时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                }
                return bl;  
            }  
        });  
        return result;  
    }  
    
    /**
     * 批量新增 使用pipeline方式   .
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#add(java.util.List)
     */
    @Override
    public boolean add(final List<EnterpriseRedisModel> enterpriseRedisModelList) throws BusinessException {  
        Assert.notEmpty(enterpriseRedisModelList);  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                for (EnterpriseRedisModel user : enterpriseRedisModelList) {  
                    byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(user.getOperatorId()));  
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
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#delete(java.lang.String)
     */
    public void delete(String operatorId) throws BusinessException {  
        List<String> list = new ArrayList<String>();  
        list.add(RedisKey.getEnterpriseLoginInfoKey(operatorId));  
        delete(list);  
    }  
  
    /**
     * 删除多个 .
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#delete(java.util.List)
     */
    public void delete(List<String> keys) throws BusinessException {  
        redisTemplate.delete(keys);  
    }  
  
    /**
     * 修改.
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#update(com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel)
     */
    public boolean update(final EnterpriseRedisModel enterpriseRedisModelList) throws BusinessException {  
    	//校验参数
        String key = enterpriseRedisModelList.getOperatorId();  
        if (get(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        }  
        
        //根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_DEFAULT + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(enterpriseRedisModelList.getOperatorId()));  
                byte[] name = serializer.serialize(JsonUtils.object2Json(enterpriseRedisModelList));  
                connection.set(key, name);  
                //设置键的有效时间
                connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()) );
                return true;  
            }  
        });  
        return result;  
    }  
  
    /**
     * 通过key获取 .
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#get(java.lang.String)
     */
    public EnterpriseRedisModel get(final String operatorId) throws BusinessException {  
    	EnterpriseRedisModel result = redisTemplate.execute(new RedisCallback<EnterpriseRedisModel>() {  
            public EnterpriseRedisModel doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(operatorId));  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String objStr = serializer.deserialize(value);  
                return JsonUtils.json2Object(objStr, EnterpriseRedisModel.class);
            }  
        });  
        return result;  
    }
    
    /**
     * 设置过期时间 （秒）.
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#expire(java.lang.String, long)
     */
    public boolean expire(final String operatorId, final long seconds) throws BusinessException {  
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(operatorId));  
                return connection.expire(key, seconds);
            }  
        });  
        return result;  
    }

    /**
     * 校验key是否存在.
     * @see com.zillionfortune.cif.service.redis.EnterpriseUserRedisService#check(java.lang.String)
     */
	public boolean check(final String operatorId) throws BusinessException {
		//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_USERINFO_EXPIRE_TIME_UPDATE + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(RedisKey.getEnterpriseLoginInfoKey(operatorId)); 
                byte[] value = connection.get(key);
                if (value == null) {  
                    return false;  
                } 
                //若键存在，则延长键的有效时间
                connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                return true;
            }  
        });  
        return result;  
	}

}  
