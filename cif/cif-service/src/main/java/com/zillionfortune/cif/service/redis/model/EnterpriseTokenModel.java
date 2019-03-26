package com.zillionfortune.cif.service.redis.model;

import java.io.Serializable;

/**
 * ClassName: TokenModel <br/>
 * Function: 企业_Token的Model类，可以增加字段提高安全性，例如时间戳、url签名. <br/>
 * Date: 2016年11月21日 下午3:59:40 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseTokenModel implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作ID
	 */
    private String operatorId;

    /**
     * 随机生成的uuid
     */
    private String token;
    
    
    public EnterpriseTokenModel() {
		super();
	}

	public EnterpriseTokenModel(String operatorId, String token) {
		super();
		this.operatorId = operatorId;
		this.token = token;
	}


	public String getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}


	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
