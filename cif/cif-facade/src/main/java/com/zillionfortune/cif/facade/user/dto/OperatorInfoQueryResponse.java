package com.zillionfortune.cif.facade.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: OperatorInfoQueryResponse <br/>
 * Function: 操作员基础信息. <br/>
 * Date: 2016年11月21日 上午10:10:48 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class OperatorInfoQueryResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2603316529872750854L;
	
	private List<OperatorInfoQueryDto> data = new ArrayList<OperatorInfoQueryDto>();
	
	public OperatorInfoQueryResponse() {
		super();
	}

	public OperatorInfoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public OperatorInfoQueryResponse(String respCode, String resultCode,
			String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public List<OperatorInfoQueryDto> getData() {
		return data;
	}

	public void setData(List<OperatorInfoQueryDto> data) {
		this.data = data;
	}

}
