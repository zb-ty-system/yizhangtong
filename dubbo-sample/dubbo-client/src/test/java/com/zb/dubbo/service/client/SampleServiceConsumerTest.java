package com.zb.dubbo.service.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zb.dubbo.facade.SampleService;
import com.zb.dubbo.facade.SampleServiceRequest;
import com.zb.dubbo.facade.SampleServiceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class SampleServiceConsumerTest {
	@Autowired
	private SampleService sampleService;

	@Test
	public void test() {
		SampleServiceRequest input = new SampleServiceRequest();
		input.setName("abc");
		SampleServiceResponse res = sampleService.echo(input);
		System.out.println(res);
		Assert.assertEquals("hi:abc", res.getEchoName());
	}

}
