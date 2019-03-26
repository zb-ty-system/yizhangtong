/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.biz.common.util;

import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.redis.RedisLock;
import com.zillionfortune.cif.common.redis.SpringRedisLock;
import com.zillionfortune.cif.dal.entity.ObjectMaxsn;
import com.zillionfortune.cif.service.ObjectMaxsnService;

/**
 * ClassName: BusinessFlowNoUtils <br/>
 * Function: 生成唯一业务编号. <br/>
 * Date: 2016年11月11日 上午9:48:40 <br/>
 *
 * @author Administrators
 * @version 
 * @since JDK 1.7
 */

@Component
public class BusinessFlowNoUtils {

	private Logger log = LoggerFactory.getLogger(BusinessFlowNoUtils.class);
	
	@Resource
	private ObjectMaxsnService objectMaxsnService;
 
    @Resource
    private JedisConnectionFactory redisConnectionFactory;
    
    /**
     * redis 结合数据库生成唯一流水号
     * @param
     * @return
     */
    public String generateSerialNo(String prefix,String tableName,String columnName,String noFormat,Long startNo) 
    	throws Exception{
    	 
    	 String flow = prefix;
    	 String sMaxNo = "";
    
    	 String sKey = tableName + "_" + columnName;

    	 SpringRedisLock lock = null;
    	 DecimalFormat decimalformat = new DecimalFormat(noFormat);
         try{
        	 
             lock = new SpringRedisLock(redisConnectionFactory,sKey.getBytes());
             
             ObjectMaxsn objectMaxsn = new ObjectMaxsn();
        	 objectMaxsn.setTableName(tableName);
        	 objectMaxsn.setColumnName(columnName);
        	 objectMaxsn.setNoFormat(noFormat);
        	 
             //分布式锁,防止同时操作数据库
             if (lock.tryLock(RedisLock.DEFAULT_TIME_OUT)) {
      
            	ObjectMaxsn returnObjectMaxsn = objectMaxsnService.selectByCriteria(objectMaxsn);
         		if(returnObjectMaxsn == null){
         			sMaxNo =  decimalformat.format(startNo);
         			
         			objectMaxsn.setMaxSerialNo(sMaxNo);
         			objectMaxsn.setCreateTime(new Date());
         			objectMaxsnService.insertSelective(objectMaxsn);
         			
         		}else{
         			
         			sMaxNo = decimalformat.format(Long.parseLong(returnObjectMaxsn.getMaxSerialNo())+1);
         			objectMaxsn.setId(returnObjectMaxsn.getId());
         			objectMaxsn.setModifyTime(new Date());
         			objectMaxsn.setMaxSerialNo(sMaxNo);
         			
         			objectMaxsnService.updateByPrimaryKeySelective(objectMaxsn);
         		
         		}
                
         		//释放分布式锁
         		lock.unlock();
         		
             } else {
                 throw new BusinessException("分布式锁" + new String(lock.getLockKey()) + "等待超时");
             }
         }catch(Exception e){
        	 log.error(e.getMessage(), e);
        	 throw e;
         } finally {
             if (lock != null)
                 lock.unlock();
         }
        
         flow += sMaxNo;
         return flow;
    }
    
    public  static void main(String[] args){
    	 DecimalFormat decimalformat = new DecimalFormat("000000");
    	 Long l = 890000L;
    	 System.out.println(decimalformat.format(l));
    }
    
}
