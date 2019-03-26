package com.zillionfortune.cif.service.user;

import com.zillionfortune.cif.dal.entity.PersonLoginLog;

/**
 * ClassName: PersonLoginLogService <br/>
 * Function: 个人_登入_日志_service接口. <br/>
 * Date: 2016年11月25日 下午4:49:41 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface PersonLoginLogService   {
	
	int insert(PersonLoginLog record);

}
