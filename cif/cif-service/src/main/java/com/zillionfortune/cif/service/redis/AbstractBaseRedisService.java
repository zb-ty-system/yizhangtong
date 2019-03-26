package com.zillionfortune.cif.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.zillionfortune.cif.service.ParameterConfigService;
  
/**  
 * AbstractBaseRedisDao 
 * @author kaiyun  
 * @version <b>1.0</b>  
 */   
public abstract class AbstractBaseRedisService<K, V> {  
      
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
    
    @Autowired
    protected ParameterConfigService parameterConfigService;
    
    
    /** 
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
    
	/** 
     * 获取 RedisSerializer 
     * <br>------------------------------<br> 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }

    protected void setParameterConfigService(
			ParameterConfigService parameterConfigService) {
		this.parameterConfigService = parameterConfigService;
	}

	 
} 