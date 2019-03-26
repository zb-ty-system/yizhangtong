package com.hyip.paycore.dal.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.dal.dao.ParameterConfigDao;
import com.zillionfortune.cif.dal.entity.ParameterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParameterConfigDaoTest extends AbstractJunitTest {
	@Resource
	private ParameterConfigDao parameterConfigDao;

	@Test
	public void test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("parameterName", "test");
		ParameterConfig systemParameterConfig = parameterConfigDao
				.selectByCriteria(map);
		System.out.println(systemParameterConfig.getParameterName() + ":"
				+ systemParameterConfig.getParameterValue());
	}

}
