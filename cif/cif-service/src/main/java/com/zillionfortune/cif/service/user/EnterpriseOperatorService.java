package com.zillionfortune.cif.service.user;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseOperator;

/**
 * ClassName: EnterpriseOperatorService <br/>
 * Function: 企业客户操作员Service. <br/>
 * Date: 2016年11月21日 下午4:21:11 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public interface EnterpriseOperatorService {

	/**
	 * queryByOperatorId:根据主键ID查询. <br/>
	 *
	 * @param id
	 * @return
	 */
	public EnterpriseOperator queryByOperatorId(Long id);

	/**
	 * queryEnterpriseOperator:根据商户号和用户名查询. <br/>
	 *
	 * @param customerNo
	 * @param loginName
	 * @return
	 */
	public EnterpriseOperator queryEnterpriseOperator(String customerNo,
			String loginName);
	
	/**
	 * 操作员信息插入
	 * @param
	 * @return
	 */
	int insertSelective(EnterpriseOperator enterpriseOperator);
	
	/**
	 * 更新操作员信息
	 * @param
	 * @return
	 */
	int updateByPrimaryKeySelective(EnterpriseOperator enterpriseOperator);
	
	/**
	 * 查询操作员信息
	 * @param
	 * @return
	 */
	public List<EnterpriseOperator> queryEnterpriseOperatorByCriteria(Map map);
}
