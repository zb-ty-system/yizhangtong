package com.zillionfortune.cif.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.dal.dao.PersonLoginLogDao;
import com.zillionfortune.cif.dal.entity.PersonLoginLog;
import com.zillionfortune.cif.service.user.PersonLoginLogService;

/**
 * ClassName: PersonLoginLogServiceImpl <br/>
 * Function: 个人_登入_日志_service实现. <br/>
 * Date: 2016年11月25日 下午4:59:25 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Service
public class PersonLoginLogServiceImpl implements PersonLoginLogService {
	
	@Autowired
	private PersonLoginLogDao personLoginLogDao;

	@Override
	public int insert(PersonLoginLog record) {
		return personLoginLogDao.insert(record);
	}

}
