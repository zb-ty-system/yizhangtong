package com.zillionfortune.cif.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.common.redis.RedisManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class CommonTest{

	@Resource
	RedisManager redisManager;
	 
	 @Test
	public void test(){
		
		 redisManager.set("name", "zhengrunlongXXX");
	}
	
}
