package com.zillionfortune.cif.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.zillionfortune.cif.common.exception.PaycoreException;
import com.zillionfortune.cif.dal.entity.ParameterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessParameterConfigServiceTest extends AbstractJunitTest {
    @Resource
    private ParameterConfigService parameterConfigService;

    @Test
    public void testQueryByParameterName() {
        ParameterConfig parameterConfig = null;
        /*try {
        	parameterConfig = parameterConfig.queryByParameterName(BusinessParameterConstants.CREDIT_CARD_RECHARGE_LIMIT_SWITCH);
        } catch (PaycoreException e) {
            e.printStackTrace();
        }*/
        System.out.println("参数名称："+parameterConfig.getParameterName()+"参数值："+parameterConfig.getParameterValue());
    }

}
