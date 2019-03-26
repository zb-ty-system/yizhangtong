package com.zillionfortune.cif.service.redis.model;

import java.io.Serializable;

/**
 * ClassName: TokenModel <br/>
 * Function: 个人_Token的Model类，可以增加字段提高安全性，例如时间戳、url签名. <br/>
 * Date: 2016年11月21日 下午3:59:40 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class TokenModel implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
    private String memberId;

    /**
     * 随机生成的uuid
     */
    private String token;
    
    
    public TokenModel() {
		super();
	}

	public TokenModel(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
