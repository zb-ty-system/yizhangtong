package com.zillionfortune.cif.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends AbstractJunitTest {
    @Resource
    private EnterpriseMemberService userService;

//    @Test
    public void testQueryByParameterName() {
    	EnterpriseMember user = null;
        try {
        	String memberId = "";
            user = userService.queryByMemberId(memberId);
            System.out.println("-----------" + user.getCustomerNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
