/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.biz.user.check.UserStatusServiceParameterChecker;
import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.UserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateResponse;
import com.zillionfortune.cif.service.user.PersonMemberService;

/**
 * ClassName: UserStatusServiceFacadeImpl <br/>
 * Function: 个人会员冻结/解冻/注销业务 对外接口实现. <br/>
 * Date: 2016年12月9日 下午2:07:57 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class UserStatusServiceFacadeImpl implements UserStatusServiceFacade {

	private static Logger log = LoggerFactory.getLogger(UserStatusServiceFacadeImpl.class);

	@Autowired
	private PersonMemberService personMemberService;

	@Autowired
	private UserStatusServiceParameterChecker parameterChecker;

	/**
	 * 会员冻结.
	 * @see com.zillionfortune.cif.facade.user.UserStatusServiceFacade#userFrozen(com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest)
	 */
	@Override
	public UserStatusUpdateResponse userFrozen(UserStatusUpdateRequest req) {
		log.info("UserStatusServiceFacadeImpl.userFrozen.req:" + JSON.toJSONString(req));
		// 响应结果定义
		UserStatusUpdateResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkUserStatusUpdateRequest(req);

			// 验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(req.getMemberId());
			if (currentPersonMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userFrozen.resp:" + JSON.toJSONString(resp));

				return resp;

			}

			// 会员状态为【正常】以外的场合，不能做冻结处理
			if (currentPersonMember.getStatus() == null
					|| currentPersonMember.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_FROZEN.code(),
						ResultCode.CAN_NOT_FROZEN.desc());

				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userFrozen.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			// 会员状态为【正常】的场合，才能做冻结操作
			PersonMember personMember = new PersonMember();
			PropertyUtils.copyProperties(personMember, req);
			// 更新会员状态（正常→冻结），进行冻结操作
			personMember.setStatus(UserStatusCodeEnum.FROZEN.getCode());
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(req.getMemberId());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new UserStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("UserStatusServiceFacadeImpl.userFrozen.resp:" + JSON.toJSONString(resp));

		return resp;

	}

	/**
	 * 会员解冻.
	 * @see com.zillionfortune.cif.facade.user.UserStatusServiceFacade#userUnfreeze(com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest)
	 */
	@Override
	public UserStatusUpdateResponse userUnfreeze(UserStatusUpdateRequest req) {
		log.info("UserStatusServiceFacadeImpl.userUnfreeze.req:" + JSON.toJSONString(req));
		// 响应结果定义
		UserStatusUpdateResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkUserStatusUpdateRequest(req);

			// 验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(req.getMemberId());
			if (currentPersonMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userUnfreeze.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			// 会员状态为【冻结】以外的场合，不能做解冻处理
			if (currentPersonMember.getStatus() == null
					|| currentPersonMember.getStatus() != UserStatusCodeEnum.FROZEN.getCode()) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_UNFREEZE.code(),
						ResultCode.CAN_NOT_UNFREEZE.desc());

				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userUnfreeze.resp:" + JSON.toJSONString(resp));

				return resp;
			}

			// 会员状态为【冻结】的场合，才能做解冻操作
			PersonMember personMember = new PersonMember();
			PropertyUtils.copyProperties(personMember, req);
			// 更新会员状态（冻结→正常），进行解冻操作
			personMember.setStatus(UserStatusCodeEnum.NORMAL.getCode());
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());

			resp.setMemberId(req.getMemberId());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new UserStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("UserStatusServiceFacadeImpl.userUnfreeze.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	/**
	 * 会员注销.
	 * @see com.zillionfortune.cif.facade.user.UserStatusServiceFacade#userCancel(com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest)
	 */
	@Override
	public UserStatusUpdateResponse userCancel(UserStatusUpdateRequest req) {
		log.info("UserStatusServiceFacadeImpl.userCancel.req:" + JSON.toJSONString(req));
		// 响应结果定义
		UserStatusUpdateResponse resp;

		try {
			// 请求参数校验
			parameterChecker.checkUserStatusUpdateRequest(req);

			// 验证会员是否存在
			PersonMember currentPersonMember = personMemberService.queryByMemberId(req.getMemberId());
			if (currentPersonMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				
				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userCancel.resp:" + JSON.toJSONString(resp));
				
				return resp;
			}
			
			// 会员状态为【正常/冻结】以外的场合，不能做注销处理
			if (currentPersonMember.getStatus() == null
				|| currentPersonMember.getStatus() == UserStatusCodeEnum.CANCEL.getCode()) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_CANCEL.code(),
						ResultCode.CAN_NOT_CANCEL.desc());
				
				resp.setMemberId(req.getMemberId());

				log.info("UserStatusServiceFacadeImpl.userCancel.resp:" + JSON.toJSONString(resp));
				
				return resp;
			}
			
			// 会员状态为【正常/冻结】的场合，才能做注销操作
			PersonMember personMember = new PersonMember();
			PropertyUtils.copyProperties(personMember, req);
			// 更新会员状态（正常/冻结→注销），进行注销操作
			personMember.setStatus(UserStatusCodeEnum.CANCEL.getCode());
			// 更改别名
			personMember.setUserName(currentPersonMember.getUserName() + CommonConstants.SUFFIX_FOR_CANCEL);
			personMember.setModifyTime(new Date());
			personMemberService.updateByMemberIdSelective(personMember);

			resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			
			resp.setMemberId(req.getMemberId());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new UserStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new UserStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}

		log.info("UserStatusServiceFacadeImpl.userCancel.resp:" + JSON.toJSONString(resp));

		return resp;
	}

}
