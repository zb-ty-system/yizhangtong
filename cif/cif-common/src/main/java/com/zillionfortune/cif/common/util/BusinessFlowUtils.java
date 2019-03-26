package com.zillionfortune.cif.common.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import com.zillionfortune.cif.common.redis.RedisManager;

/**
 * ClassName: RedisLock <br/>
 * Function: 业务流水生成. <br/>
 * Date: 2016年11月5日 上午9:43:49 <br/>
 *
 * @author Administrators
 * @version 
 * @since JDK 1.7
 */
public class BusinessFlowUtils {

	@Resource
	private static RedisManager redisManager;
	 
    /**
     * 生成UUID流水号
     *
     * @param prefix
     * @return
     */
    public static String genereteUUID(String prefix){
    	String flow = prefix;
    	UUID uuid = UUID.randomUUID();   
    	flow += uuid.toString();
    	
    	return flow;
    }
    
    /**
     * 生成业务流水号
     *
     * @param prefix
     * @return
     */
    public static String generate(String prefix) {
        String flow = prefix;
        //时间戳
        flow += DateUtil.dateToDateString(new Date(), DateUtil.DATATIMEF_STR_MIS);
     
        //获得随机数
        double pross = (1 + new Random().nextDouble()) * Math.pow(10, 7);
        flow += String.valueOf(pross).substring(2, 9);
        return flow;
    }
    
    public static void main(String[] args){
    	System.out.println(BusinessFlowUtils.generate(""));
    	
    	for(int i=0 ; i<5; i++){
    		UUID uuid = UUID.randomUUID();   
        	String str = uuid.toString();
        	
        	System.out.println(str+"==="+str.length());
    	}
    	
    }
    
}
