package com.zillionfortune.cif.service.user;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;

public interface EnterpriseMemberService {

	/**
	 * queryList:查询列表. <br/>
	 *
	 * @param enterpriseMember
	 * @return
	 */
	public List<EnterpriseMember> queryList(EnterpriseMember enterpriseMember);

	/**
	 * queryEnterpriseMember:查询对象. <br/>
	 *
	 * @param enterpriseMember
	 * @return
	 */
	public EnterpriseMember queryEnterpriseMember(
			EnterpriseMember enterpriseMember);

	/**
	 * queryByMemberId:根据memberId查询. <br/>
	 *
	 * @param memberId
	 * @return
	 */
	public EnterpriseMember queryByMemberId(String memberId);
	
	/**
	 * 插入会员信息
	 * @param
	 * @return
	 */
	int insertSelective(EnterpriseMember record);
	
	/**
	 * updateGrade:更新等级 <br/>
	 *
	 * @param memberId
	 * @param grade 原会员等级串值
	 * @param val 目标值
	 * @param index 从1开始（ 更新位置）
	 */
	public void updateGrade(String memberId,String grade, String val, int index);

	/**
	 * getGrade:查询等级<br/>
	 *
	 * @param memberId
	 * @param index
	 *            从1开始
	 * @return
	 */
	public String getGrade(String memberId, int index);
	
	/**
	 * updateByMemberIdSelective:根据MemberId选择性更新. <br/>
	 *
	 * @param record
	 * @return
	 */
	public int updateByMemberIdSelective(EnterpriseMember record);
	
	/**
	 * getEnterpriseMemberCount:根据memberId和会员状态，查找会员数量. <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	public int getEnterpriseMemberCount(Map paramMap);
	
	/**
	 * queryInfoByPage:企业会员信息列表分页查询  . <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	public List<EnterpriseInfoMember> queryInfoByPage(Map paramMap);
	
	/**
	 * queryInfoByPageCount:企业会员信息列表分页查询  总数. <br/>
	 *
	 * @param paramMap
	 * @return
	 */
	public long queryInfoByPageCount(Map paramMap);
}
