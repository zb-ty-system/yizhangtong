package com.zillionfortune.cif.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.dal.dao.EnterpriseOperatorDao;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;

@Service
public class EnterpriseOperatorServiceImpl implements EnterpriseOperatorService {

	@Autowired
	private EnterpriseOperatorDao enterpriseOperatorDao;

	@Override
	public EnterpriseOperator queryByOperatorId(Long id) {
		return enterpriseOperatorDao.selectByPrimaryKey(id);
	}

	/**
	 * @see com.zillionfortune.cif.service.user.EnterpriseOperatorService#queryEnterpriseOperator(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public EnterpriseOperator queryEnterpriseOperator(String customerNo,
			String loginName) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("userName", loginName);
		criteria.put("customerNo", customerNo);
		List<EnterpriseOperator> list = enterpriseOperatorDao
				.selectByCriteria(criteria);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int insertSelective(EnterpriseOperator enterpriseOperator) {
		
		return enterpriseOperatorDao.insertSelective(enterpriseOperator);
	}

	@Override
	public int updateByPrimaryKeySelective(EnterpriseOperator enterpriseOperator) {
		
		return enterpriseOperatorDao.updateByPrimaryKeySelective(enterpriseOperator);
	}

	@Override
	public List<EnterpriseOperator> queryEnterpriseOperatorByCriteria(Map map) {
		
		List<EnterpriseOperator> list = enterpriseOperatorDao.selectByCriteria(map);
		
		return list;
	}

}

