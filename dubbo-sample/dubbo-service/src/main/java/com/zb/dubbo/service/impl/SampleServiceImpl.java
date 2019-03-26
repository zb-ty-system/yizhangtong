package com.zb.dubbo.service.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zb.dubbo.facade.SampleService;
import com.zb.dubbo.facade.SampleServiceRequest;
import com.zb.dubbo.facade.SampleServiceResponse;

/**
 * Created by zhuwenbing on 2017/2/23 0023.
 */
@Service
public class SampleServiceImpl implements SampleService {
    @Override
    public SampleServiceResponse echo(SampleServiceRequest input) {
    	SampleServiceResponse res = new SampleServiceResponse();
    	res.setEchoName("hi:" + input.getName());
        return res;
    }

    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo.xml"});
        context.start();
        System.out.println("提供者服务已注册成功");
        try {
            System.in.read();//让此程序一直跑，表示一直提供服务
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
