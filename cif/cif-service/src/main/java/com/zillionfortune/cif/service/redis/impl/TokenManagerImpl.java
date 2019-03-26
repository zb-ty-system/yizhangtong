package com.zillionfortune.cif.service.redis.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.constants.RedisKey;
import com.zillionfortune.cif.common.constants.SystemParameterConstants;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.ParameterConfig;
import com.zillionfortune.cif.service.ParameterConfigService;
import com.zillionfortune.cif.service.redis.AbstractBaseRedisService;
import com.zillionfortune.cif.service.redis.TokenManager;
import com.zillionfortune.cif.service.redis.model.TokenModel;


/**
 * ClassName: RedisTokenManager <br/>
 * Function: 个人_对Token进行操作的接口实现. <br/>
 * Date: 2016年11月21日 下午4:00:25 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Component
public class TokenManagerImpl extends AbstractBaseRedisService<String, String> implements TokenManager {
	
	@Autowired
	ParameterConfigService parameterConfigService;

    @Override
    public TokenModel createToken(final String memberId) throws BusinessException {
    	//校验参数
    	if (StringUtils.isBlank(memberId)) {
    		throw new BusinessException("memberId为空！");
        }
    	
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_DEFAULT);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_DEFAULT+"],查找不到参数表[ParameterConfig]记录！");
    	}
    	
    	TokenModel result = redisTemplate.execute(new RedisCallback<TokenModel>() {  
            public TokenModel doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
            	//使用uuid作为源token
            	String token = UUID.randomUUID().toString().replace("-", "");
            	TokenModel model = new TokenModel(memberId, token);
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(memberId));  
                byte[] value = serializer.serialize(token);  
                boolean bl = connection.setNX(key, value);  
                //设置键的有效时间
                if(bl){
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()));
                }
                return model;
            }  
        });  
        return result;  
    }

    @Override
    public String getTokenByKey(final String memberId) throws BusinessException {
    	if (StringUtils.isBlank(memberId)) {
    		throw new BusinessException("memberId为空！");
        }
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(memberId));  
                //判别键值是否存在，避免无用功
                if (connection.exists(key)) {
                	return serializer.deserialize(connection.get(key));
                }
                return null;
            }  
        });  
        return result;  
    }
    
    @Override
    public boolean checkToken(final String memberId) throws BusinessException {
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_UPDATE+"],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(memberId)); 
                if (connection.exists(key)) {
                	//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()) );
                	return true;
                }
                return false;
            }  
        });  
        return result;  
    }

    @Override
    public boolean checkToken(final TokenModel tokenModel) throws BusinessException {
    	//根据参数名获取参数表信息
    	final ParameterConfig parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_UPDATE);
    	if(parameterConfig==null){
    		throw new BusinessException("根据参数名["+SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_UPDATE+"],查找不到参数表[ParameterConfig]记录！");
    	}
    	
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(tokenModel.getMemberId())); 
                if (connection.exists(key)) {
                	String token =serializer.deserialize(connection.get(key));
                	if (token == null || !token.equals(tokenModel.getToken())) {
                        return false;
                    } 
                	//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
                	connection.expire(key, Long.parseLong(parameterConfig.getParameterValue()) );
                	return true;
                }
                return false;
            }  
        });  
        return result;  
    }
    
    @Override
    public boolean checkToken(final String keyStr, final String tokenStr) throws BusinessException {
    	boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	    	public Boolean doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = getRedisSerializer();  
	            byte[] key  = serializer.serialize(keyStr);
	            if (connection.exists(key)) {
	            	String token = serializer.deserialize(connection.get(key));
	            	if (token == null || !token.equals(tokenStr)) {
	                    return false;
	                } 
	            	return true;
	            }
	            return false;
	        }  
	    }); 
    	return result;  
    }

    @Override
    public long deleteToken(final String memberId) throws BusinessException {
    	Long result = redisTemplate.execute(new RedisCallback<Long>() {  
	    	public Long doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = getRedisSerializer();  
	            byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(memberId));
	            long num = connection.del(key);
	            return num;
	        }  
	    }); 
    	return result;
    }

	@Override
	public boolean expire(final String memberId, final long seconds) throws BusinessException {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	    	public Boolean doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = getRedisSerializer();  
	            byte[] key  = serializer.serialize(RedisKey.getIndividualLoginTokenKey(memberId));
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

