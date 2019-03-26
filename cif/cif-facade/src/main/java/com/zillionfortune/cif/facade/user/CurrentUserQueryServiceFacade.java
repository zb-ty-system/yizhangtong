package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceResponse;

/**
 * ClassName: CurrentUserQueryServiceFacade <br/>
 * Function: 会员登录信息查询接口. <br/>
 * Date: 2016年11月15日 下午4:42:55 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface CurrentUserQueryServiceFacade {
	
	/**
	 * query:会员登录信息查询. <br/>
	 *
	 * @param req
	 * @return
	 */
	public CurrentUserQueryServiceResponse query(CurrentUserQueryServiceRequest req);
}
