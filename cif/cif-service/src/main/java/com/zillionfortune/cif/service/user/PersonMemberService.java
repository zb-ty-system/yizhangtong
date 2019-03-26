package com.zillionfortune.cif.service.user;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.PersonMember;

/**
 * ClassName: PersonMemberService <br/>
 * Function:个人会员. <br/>
 * Date: 2016年11月21日 上午11:02:38 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public interface PersonMemberService {

	/**
	 * queryByMemberId:根据memberID查询. <br/>
	 *
	 * @param memberId
	 * @return
	 */
	public PersonMember queryByMemberId(String memberId);

	/**
	 * queryList:根据各种条件查询结果集. <br/>
	 *
	 * @param personMember
	 * @return
	 */
	public List<PersonMember> queryList(PersonMember personMember);

	/**
	 * queryPersonMember:根据条件查询结果对象. <br/>
	 *
	 * @param personMember
	 * @return
	 */
	public PersonMember queryPersonMember(PersonMember personMember);

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
	 * getGrade:查询等级. <br/>
	 *
	 * @param memberId
	 * @param index
	 *            从1开始
	 * @return
	 */
	public String getGrade(String memberId, int index);

	/**
	 * 查询会员数
	 * @param
	 * @return
	 */
	public int getPersonMemberCount(Map paramMap);
	
	/**
	 * 插入会员信息
	 * @param
	 * @return
	 */
	public int insertSelective(PersonMember personMember);
	
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
