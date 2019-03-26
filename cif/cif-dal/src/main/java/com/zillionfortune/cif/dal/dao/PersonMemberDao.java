package com.zillionfortune.cif.dal.dao;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface PersonMemberDao {
	int deleteByPrimaryKey(Long id);

	int insert(PersonMember record);

	int insertSelective(PersonMember record);

	PersonMember selectByPrimaryKey(Long id);

	PersonMember selectByCustomerId(String customerId);

	List<PersonMember> selectByCriteria(PersonMember record);

	int updateByPrimaryKeySelective(PersonMember record);

	int updateByPrimaryKey(PersonMember record);
	
	/**
	 * 查询会员数
	 * @param
	 * @return
	 */
	int getPersonMemberCount(Map paramMap);
	
	/**
	 * 更新个人会员注册信息
	 * @param
	 * @return
	 */
	int updateByMemberIdSelective(PersonMember personMember);
	
	/**
	 * 根据会员编号查询会员信息
	 * @param
	 * @return
	 */
	PersonMember selectByMemberId(String memberId);
}