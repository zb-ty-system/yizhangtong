package com.zillionfortune.cif.service.redis;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.service.redis.model.TokenModel;

/**
 * ClassName: TokenManager <br/>
 * Function: 个人_对Token进行操作的接口. <br/>
 * Date: 2016年11月21日 下午4:00:07 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface TokenManager {

	/**
	 * createToken:创建一个token,关联上指定用户. <br/>
	 *
	 * @param memberId 会员id
	 * @return TokenModel
	 */
    public TokenModel createToken(String memberId) throws BusinessException ;
    
    /**
     * checkToken:检查token是否有效. <br/>
     *
     * @param memberId 会员id
     * @return boolean
     */
    public boolean checkToken(String memberId) throws BusinessException ;

    /**
     * checkToken:检查token是否有效. <br/>
     *
     * @param TokenModel Token的Model类
     * @return boolean
     */
    public boolean checkToken(TokenModel TokenModel) throws BusinessException ;
    
    /**
     * checkToken:检查token是否有效. <br/>
     *
     * @param key 令牌key
     * @param token 令牌value
     * @return boolean
     */
    public boolean checkToken(String key, String token) throws BusinessException;

    /**
     * getToken:根据key查Token. <br/>
     *
     * @param memberId 会员id
     * @return token
     */
    public String getTokenByKey(String memberId) throws BusinessException ;

    /**
     * deleteToken:清除token. <br/>
     *
     * @param memberId 会员id
     * @return 删除key的数量
     */
    public long deleteToken(String memberId) throws BusinessException ;
    
    /**  
     * 设置键的有效时间（秒）
     * <br>------------------------------<br> 
     * @param memberId 会员id
     * @param seconds 有效时间（单位：秒）
     * @return boolean
     */ 
    boolean expire(String memberId, long seconds) throws BusinessException;
    
}

