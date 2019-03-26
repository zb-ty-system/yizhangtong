package com.zillionfortune.cif.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.common.constants.SystemParameterConstants;
import com.zillionfortune.cif.dal.entity.ParameterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParameterConfigServiceTest extends AbstractJunitTest {
    @Resource
    private ParameterConfigService parameterConfigService;

//    @Test
    public void testQueryByParameterName() {
        ParameterConfig parameterConfig = null;
        try {
        	parameterConfig = parameterConfigService.queryByParameterName(SystemParameterConstants.PERSON_TOKEN_EXPIRE_TIME_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("参数名称："+parameterConfig.getParameterName()+",参数值："+parameterConfig.getParameterValue());
    }

}
