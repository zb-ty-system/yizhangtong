/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.common.constants;

/**
 * ClassName: CommonConstants <br/>
 * Function: 公用常量. <br/>
 * Date: 2016年11月9日 下午2:12:05 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class CommonConstants {

	/** 性别：男  */
    public final static Integer MALE= 1;
    
    /** 性别：女  */
    public final static Integer FEMALE= 2;
    
    /** 婚姻状态：未婚  */
    public final static Integer UNMARRIED= 0;
    
    /** 婚姻状态：已婚； */
    public final static Integer MARRIED= 1;
    
    /** 个人会员状态默认：00000000； */
    public final static String PERSON_MEMBER_GRADE= "00000000";
    
    /** 企业会员状态默认：00000000； */
    public final static String ENTERPRISE_MEMBER_GRADE= "00000000";
    
    /** 表的名称 */
	public static final String ENTERPRISE_MEMBER = "ENTERPRISE_MEMBER";
	
	/** 表的字段 */
	public static final String ENTERPRISE_MEMBER_CUSTOMER_NO = "CUSTOMER_NO";
	
	/** 编号格式 */
	public static final String ENTERPRISE_MEMBER_CUSTOMER_NO_NO_FORMAT = "000000";
	
	/** 编号起始值 */
	public static final Long ENTERPRISE_MEMBER_CUSTOMER_NO_START_ID = 100000L;
	
	/**
	 * SUFFIX_FOR_CANCEL:注销用后缀.
	 */
	public static final String SUFFIX_FOR_CANCEL = "_^%^";
	
	/** 企业客户编号前缀 */
	public static final String ENTERPRISE_INFO_PREFIX = "EC";
	
	/** 企业会员编号前缀 */
	public static final String ENTERPRISE_MEMBER_PREFIX = "EM";
	
	/** 个人客户编号前缀 */
	public static final String PERSONAL_INFO_PREFIX = "PC";
	
	/** 个人会员编号前缀 */
	public static final String PERSONAL_MEMBER_PREFIX = "PM";
	
}
