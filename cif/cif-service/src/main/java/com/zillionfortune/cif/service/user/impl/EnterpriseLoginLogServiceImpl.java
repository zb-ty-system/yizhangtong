package com.zillionfortune.cif.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.dal.dao.EnterpriseLoginLogDao;
import com.zillionfortune.cif.dal.entity.EnterpriseLoginLog;
import com.zillionfortune.cif.service.user.EnterpriseLoginLogService;

/**
 * ClassName: EnterpriseLoginLogServiceImpl <br/>
 * Function: 个人_登入_日志_service实现. <br/>
 * Date: 2016年11月25日 下午5:00:22 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
@Service
public class EnterpriseLoginLogServiceImpl implements EnterpriseLoginLogService {
	
	@Autowired
	private EnterpriseLoginLogDao enterpriseLoginLogDao;

	@Override
	public int insert(EnterpriseLoginLog record) {
		return enterpriseLoginLogDao.insert(record);
	}

}
