/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.common.util;

import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: TransResult <br/>
 * Function: 支付系统返回结果转换处理. <br/>
 * Date: 2016年12月13日 上午9:53:17 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class TransResult {

	/**
	 * transPaymentResult:转换结果提示消息 . <br/>
	 *
	 * @param resp
	 */
	public static void transPaymentResult(BaseResponse resp) {
		// 支付网关系统异常
		if(resp.getRespCode().equals(RespCode.FAIL.code())) {
			resp.setRespCode(RespCode.SUCCESS.code());
			resp.setResultCode(ResultCode.PAYMENT_ERROR.code());
			resp.setResultMsg(ResultCode.PAYMENT_ERROR.desc());
		} else if (resp.getRespCode().equals(RespCode.SUCCESS.code()) && resp.getResultCode().equals(ResultCode.PAYMENT_FAIL.code())) { // 支付网关处理 失败
			resp.setResultMsg(ResultCode.PAYMENT_FAIL.desc());
		}
	}
}
