package com.zillionfortune.cif.dal.dao;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface EnterpriseMemberDao {
	int deleteByPrimaryKey(Long id);

	int insert(EnterpriseMember record);

	int insertSelective(EnterpriseMember record);

	EnterpriseMember selectByPrimaryKey(Long id);

	List<EnterpriseMember> selectByCriteria(EnterpriseMember recode);

	int updateByPrimaryKeySelective(EnterpriseMember record);

	int updateByPrimaryKey(EnterpriseMember record);
	
	/**
	 * updateByMemberIdSelective:根据MemberId选择性更新. <br/>
	 *
	 * @param record
	 * @return
	 */
	int updateByMemberIdSelective(EnterpriseMember record);
	
	/**
	 * getEnterpriseMemberCount:根据memberId和会员状态，查找会员数量. <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	int getEnterpriseMemberCount(Map paramMap);
	
	/**
	 * updateAuthGradeByCustomerId:根据customerID更新认证等级. <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	int updateAuthGradeByCustomerId(Map paramMap);
	
	/**
	 * queryInfoByPage:企业会员信息列表分页查询  . <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	List<EnterpriseInfoMember> queryInfoByPage(Map paramMap);
	
	/**
	 * queryInfoByPageCount:企业会员信息列表分页查询  总数. <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	long queryInfoByPageCount(Map paramMap);
}