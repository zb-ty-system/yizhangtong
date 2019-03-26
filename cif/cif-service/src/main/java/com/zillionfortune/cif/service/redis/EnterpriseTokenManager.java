package com.zillionfortune.cif.service.redis;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.service.redis.model.EnterpriseTokenModel;

/**
 * ClassName: TokenManager <br/>
 * Function: 企业_对Token进行操作的接口. <br/>
 * Date: 2016年11月21日 下午4:00:07 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseTokenManager {
	

	/**
	 * createToken:创建一个token关联上指定用户. <br/>
	 *
	 * @param operatorId 操作员Id
	 * @return EnterpriseTokenModel
	 */
    public EnterpriseTokenModel createToken(String operatorId) throws BusinessException ;
    
    /**
     * checkToken:检查token是否存在. <br/>
     *
     * @param model token
     * @return boolean
     */
    public boolean checkToken(String operatorId) throws BusinessException ;

    /**
     * checkToken:检查token是否有效. <br/>
     *
     * @param model token
     * @return boolean
     */
    public boolean checkToken(EnterpriseTokenModel model) throws BusinessException ;

    /**
     * getToken:根据key查Token. <br/>
     *
     * @param operatorId 操作员Id
     * @return token
     */
    public String getTokenByKey(String operatorId) throws BusinessException ;

    /**
     * deleteToken:清除token. <br/>
     *
     * @param operatorId 操作员Id
     * @return 删除key的数量
     */
    public long deleteToken(String operatorId) throws BusinessException ;
    
    /**  
     * expire:设置键的有效时间（秒） <br/>
     * 
     * @param operatorId 操作员Id
     * @param seconds 有效时间（单位：秒）
     * @return boolean
     */ 
    boolean expire(String operatorId, long seconds) throws BusinessException;

}
