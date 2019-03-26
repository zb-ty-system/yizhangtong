package com.zb.dubbo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath:dubbo.xml", "classpath:applicationContext.xml"})
public class BootServiceStart {
	private final static Logger logger = LoggerFactory.getLogger(BootServiceStart.class);
	
	public static void main(String[] args) throws InterruptedException {
		 ApplicationContext ctx = new SpringApplicationBuilder()
	                .sources(BootServiceStart.class)
	                .web(false)
	                .run(args);

		 logger.info("项目启动!");

	     new CountDownLatch(1).await();
	}
}
