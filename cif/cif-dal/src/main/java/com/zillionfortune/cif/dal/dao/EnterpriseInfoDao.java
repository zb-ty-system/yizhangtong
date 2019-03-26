package com.zillionfortune.cif.dal.dao;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface EnterpriseInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(EnterpriseInfo record);

	int insertSelective(EnterpriseInfo record);

	EnterpriseInfo selectByPrimaryKey(Long id);

	EnterpriseInfo selectByMemberId(String memberId);

	EnterpriseInfo selectByCriteria(EnterpriseInfo record);

	int updateByPrimaryKeySelective(EnterpriseInfo record);

	int updateByPrimaryKey(EnterpriseInfo record);
	
	int updateByCustomerIdSelective (EnterpriseInfo record);
	
	/**
	 * queryUserAuthInfoByPage:分页查询 认证信息列表. <br/>
	 *
	 * @param map
	 * @return
	 */
	List<EnterpriseInfoMember> queryUserAuthInfoByPage(Map<String, Object> map);
	
	/**
	 * queryUserAuthInfoByPageCount:分页查询 认证信息总数. <br/>
	 *
	 * @param map
	 * @return
	 */
	long  queryUserAuthInfoByPageCount(Map<String, Object> map);
	
	/**
	 * selectListByCriteria:查询列表. <br/>
	 *
	 * @param map
	 * @return
	 */
	List<EnterpriseInfo> selectListByCriteria(Map map);
}