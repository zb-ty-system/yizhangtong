package com.zillionfortune.cif.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zillionfortune.cif.dal.entity.EnterpriseLoginLog;
import com.zillionfortune.cif.dal.entity.PersonLoginLog;
import com.zillionfortune.cif.service.user.EnterpriseLoginLogService;
import com.zillionfortune.cif.service.user.PersonLoginLogService;

/**
 * ClassName: LoginLogServiceTest <br/>
 * Function: 登入日志. <br/>
 * Date: 2016年11月28日 上午10:38:06 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginLogServiceTest extends AbstractJunitTest {
    
    @Autowired
    private PersonLoginLogService personLoginLogService;
    
    @Autowired
    private EnterpriseLoginLogService enterpriseLoginLogService;


//    @Test
    public void test_person_opertor_insert_log(){
    	personLoginLogService.insert(new PersonLoginLog("kaiyun1",new Date()) );
    }
    
//    @Test
    public void test_enterprise_opertor_insert_log(){
    	enterpriseLoginLogService.insert(new EnterpriseLoginLog("kaiyun1","111111",new Date()) );
    }

}
