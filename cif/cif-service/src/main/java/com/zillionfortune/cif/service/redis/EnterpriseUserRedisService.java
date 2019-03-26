package com.zillionfortune.cif.service.redis;

import java.util.List;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.service.redis.model.EnterpriseRedisModel;

/**
 * ClassName: EnterpriseUserRedisService <br/>
 * Function: redis_企业用户信息service操作_接口. <br/>
 * Date: 2016年12月1日 下午3:45:13 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version @param <E>
 * @since JDK 1.7
 */
public interface EnterpriseUserRedisService {  
      
    /** 
     * 新增 
     * <br>------------------------------<br> 
     * @param enterpriseRedisModel 企业用户信息model类
     * @return boolean 
     */
    boolean add(EnterpriseRedisModel enterpriseRedisModel) throws BusinessException;  
    
    /** 
     * 批量新增 使用pipeline方式 
     * <br>------------------------------<br> 
     * @param enterpriseRedisModelList 企业用户信息model列表
     * @return boolean
     */  
    boolean add(List<EnterpriseRedisModel> enterpriseRedisModelList) throws BusinessException;  
      
    /** 
     * 删除 
     * <br>------------------------------<br> 
     * @param operatorId 操作员主键ID
     */  
    void delete(String operatorId) throws BusinessException;  
      
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param operatorIdList 操作员主键ID列表
     */  
    void delete(List<String> operatorIdList) throws BusinessException;  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     * @param enterpriseRedisModel 企业用户信息model类
     * @return boolean
     */  
    boolean update(EnterpriseRedisModel enterpriseRedisModel) throws BusinessException;  
  
    /** 
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param operatorId 操作员主键ID
     * @return EnterpriseRedisModel 
     */  
    EnterpriseRedisModel get(String operatorId) throws BusinessException;  
    
    /**  
     * 设置过期时间 （秒）
     * <br>------------------------------<br> 
     * @param operatorId 操作员主键ID 
     * @param seconds 
     * @return boolean
     */  
    boolean expire(String operatorId, long seconds) throws BusinessException;
    
    /**  
     * 校验key是否存在
     * <br>------------------------------<br> 
     * @param operatorId 操作员主键ID 
     * @return 
     */ 
    boolean check(String operatorId) throws BusinessException;
    
}  
