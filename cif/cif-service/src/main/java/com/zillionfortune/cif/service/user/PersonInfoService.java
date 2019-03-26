package com.zillionfortune.cif.service.user;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.PersonInfo;

public interface PersonInfoService {

	/**
	 * queryByMemberId:根据memberId 查询. <br/>
	 *
	 * @param memberId
	 * @return
	 */
	public PersonInfo queryByMemberId(String memberId);

	/**
	 * queryByCustomerId:根据CustomerId查询. <br/>
	 *
	 * @param customerId
	 * @return
	 */
	public PersonInfo queryByCustomerId(String customerId);

	/**
     * 根据CustomerId更新客户扩展信息
     * @param
     * @return
     */
    int updateByCustomerIdSelective(PersonInfo personInfo);
    
    public List<PersonInfo> queryByCriteria(Map<String, Object> criteria);
}
