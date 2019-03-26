package com.zillionfortune.cif.service.redis.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.constants.RedisKey;
import com.zillionfortune.cif.common.constants.SystemParameterConstants;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.ParameterConfig;
import com.zillionfortune.cif.service.redis.AbstractBaseRedisService;
import com.zillionfortune.cif.service.redis.EnterpriseTokenManager;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;


/**
 * ClassName: RedisTokenManager <br/>
 * Function: 企业_对Token进行操作的接口实现. <br/>
 * Date: 2016年11月21日 下午4:00:25 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseTokenManagerImpl extends AbstractBaseRedisService<String, String> implements EnterpriseTokenManager {
	
	/**
	 * TODO 企业_创建一个token关联上指定用户.
	 * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#createToken(java.lang.String)
	 */
    @Override
    public EnterpriseTokenModel createToken(final String operatorId) throws BusinessException {
    	//校验参数
        if (StringUtils.isBlank(operatorId)) {
    		throw new BusinessException("operatorId为空！");
        }
        
        //根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_DEFAULT + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        EnterpriseTokenModel result = redisTemplate.execute(new RedisCallback<EnterpriseTokenModel>() {  
            public EnterpriseTokenModel doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
            	//使用uuid作为源token
            	String token = UUID.randomUUID().toString().replace("-", "");
            	EnterpriseTokenModel model = new EnterpriseTokenModel(operatorId, token);
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(operatorId));  
                byte[] value = serializer.serialize(token);  
                boolean bl = connection.setNX(key, value);  
                if(bl){//设置键的有效时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                }
                return model;
            }  
        });  
        return result;
    }

    /**
     * TODO 企业_检查token是否有效.
     * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#getTokenByKey(java.lang.String)
     */
    @Override
    public String getTokenByKey(final String operatorId) throws BusinessException {
        if (StringUtils.isBlank(operatorId)) {
    		throw new BusinessException("operatorId为空！");
        }
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(operatorId));  
                //判别键值是否存在，避免无用功
                if (connection.exists(key)) {
                	return serializer.deserialize(connection.get(key));
                }
                return null;
            }  
        });  
        return result; 
    }
    
    /**
     * 校验Token是否存在
     * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#checkToken(com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel)
     */
    @Override
    public boolean checkToken(final String operatorId) throws BusinessException {
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_UPDATE + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(operatorId)); 
                if (connection.exists(key)) {
                	//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                	return true;
                }
                return false;
            }  
        });  
        return result;  
    }

    /**
     * TODO 企业_根据key查Token.
     * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#checkToken(com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel)
     */
    @Override
    public boolean checkToken(final EnterpriseTokenModel model) throws BusinessException {
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名[" + SystemParameterConstants.ENTERPRISE_TOKEN_EXPIRE_TIME_UPDATE + "],查找不到参数表[ParameterConfig]记录！");
    	}
    	
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(model.getOperatorId())); 
                if (connection.exists(key)) {
                	String token =serializer.deserialize(connection.get(key));
                	if (token == null || !token.equals(model.getToken())) {
                        return false;
                    } 
                	//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                	return true;
                }
                return false;
            }  
        });  
        return result;  
    }

    /**
     * TODO 企业_清除token.
     * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#deleteToken(java.lang.String)
     */
    @Override
    public long deleteToken(final String operatorId) throws BusinessException {
    	Long result = redisTemplate.execute(new RedisCallback<Long>() {  
	    	public Long doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = getRedisSerializer();  
	            byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(operatorId));
	            long num = connection.del(key);
	            return num;
	        }  
	    }); 
    	return result;
    }
    
    /**
     * TODO 设置键的有效时间（秒）.
     * @see com.zillionfortune.cif.service.redis.EnterpriseTokenManager#expire(java.lang.String, long)
     */
    @Override
	public boolean expire(final String operatorId, final long seconds) throws BusinessException {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	    	public Boolean doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = getRedisSerializer();  
	            byte[] key  = serializer.serialize(RedisKey.getEnterpriseLoginTokenKey(operatorId));
	            if (connection.exists(key)) {
	            	if(seconds!=0){
	            		return connection.expire(key, seconds);
	            	}
	            }
	            return false;
	        }  
	    }); 
    	return result; 
	}
}
