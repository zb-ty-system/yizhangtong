package com.zillionfortune.cif.service.user;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;

/**
 * ClassName: EnterprisetInfoService <br/>
 * Function:企业客户信息Service. <br/>
 * Date: 2016年11月21日 下午4:19:01 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7  
 */
public interface EnterpriseInfoService {

	/**
	 * queryEnterpriseInfo:根据条件查询对象. <br/>
	 *
	 * @param enterpriseInfo
	 * @return
	 */
	EnterpriseInfo queryEnterpriseInfo(EnterpriseInfo enterpriseInfo);

	/**
	 * queryByCustomerId:根据customerID查询. <br/>
	 *
	 * @param customerId
	 * @return
	 */
	EnterpriseInfo queryByCustomerId(String customerId);

	/**
	 * queryByMemberId:根据memberId查询. <br/>
	 *
	 * @param memberId
	 * @return
	 */
	EnterpriseInfo queryByMemberId(String memberId);
	
	/**
	 * 企业扩展信息插入
	 * @param
	 * @return
	 */
	int insertSelective(EnterpriseInfo enterpriseInfo);
	
	/**
	 * 根据CustomerId更新企业扩展信息
	 * @param
	 * @return
	 */
	int updateByCustomerIdSelective (EnterpriseInfo enterpriseInfo);

	/**
	 * 落地企业客户、会员、操作员信息
	 * @param
	 * @return
	 */
	void register(EnterpriseInfo enterpriseInfo,EnterpriseMember enterpriseMember,EnterpriseOperator enterpriseOperator);
	
	/**
	 * 落会员、操作员信息
	 * @param
	 * @return
	 */
	void register(EnterpriseMember enterpriseMember,EnterpriseOperator enterpriseOperator);
	
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
	 * auditAuth:审核认证. <br/>
	 *
	 * @param enterpriseInfo
	 * @param authGrade
	 * @return 认证等级
	 */
	String auditAuth(EnterpriseInfo enterpriseInfo, String authGrade);
	
	/**
	 * selectListByCriteria:查询列表. <br/>
	 *
	 * @param map
	 * @return
	 */
	List<EnterpriseInfo> selectListByCriteria(Map map);
}

