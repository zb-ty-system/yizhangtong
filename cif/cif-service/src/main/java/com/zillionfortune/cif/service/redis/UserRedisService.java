package com.zillionfortune.cif.service.redis;

import java.util.List;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.service.redis.model.IndividualRedisModel;

/**
 * ClassName: UserRedisService <br/>
 * Function: redis_个人用户信息service操作_接口. <br/>
 * Date: 2016年12月1日 下午3:43:30 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version @param <E>
 * @since JDK 1.7
 */
public interface UserRedisService {  
      
    /** 
     * 新增 
     * <br>------------------------------<br> 
     * @param individualRedisModel 个人用户信息model
     * @return boolean
     */  
    boolean add(IndividualRedisModel individualRedisModel) throws BusinessException;  
    
    /** 
     * 批量新增 使用pipeline方式 
     * <br>------------------------------<br> 
     * @param individualRedisModelList 个人用户信息model列表
     * @return boolean
     */  
    boolean add(List<IndividualRedisModel> individualRedisModelList) throws BusinessException;  
      
    /** 
     * 删除 
     * <br>------------------------------<br> 
     * @param memberId 会员ID 
     */  
    void delete(String memberId) throws BusinessException;  
      
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param memberIdList 会员ID列表
     */  
    void delete(List<String> memberIdList) throws BusinessException;  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     * @param individualRedisModel 个人用户信息model
     * @return boolean
     */  
    boolean update(IndividualRedisModel individualRedisModel) throws BusinessException;  
  
    /** 
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param memberId 会员ID  
     * @return IndividualRedisModel 
     */  
    IndividualRedisModel get(String memberId) throws BusinessException; 
    
    /**  
     * 设置过期时间 （秒）
     * <br>------------------------------<br> 
     * @param memberId 会员ID  
     * @param seconds 
     * @return boolean
     */  
    boolean expire(String memberId, long seconds) throws BusinessException;
    
    /**  
     * 校验key是否存在
     * <br>------------------------------<br> 
     * @param memberId 会员ID
     * @return boolean
     */ 
    boolean check(String memberId) throws BusinessException;
}  
