package com.zillionfortune.cif.common.constants;

/**
 * ClassName: AuthorizationConstants <br/>
 * Function: 登入授权常量. <br/>
 * Date: 2016年11月21日 下午4:09:17 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class RedisConstants {
	
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（分钟）
     */
    public static final int TOKEN_EXPIRES_MINUTES = 30;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    
    /**
     * 初始化key的有效期（1800秒）
     */
    public static final long EXPIRE_SECOND_DEFAULT = 1800;

}
