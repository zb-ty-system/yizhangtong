package com.zillionfortune.cif.common.enums;

/**
 * ClassName: AuthGrade <br/>
 * Function: 企业会员认证类型. <br/>
 * Date: 2016年11月22日 上午10:21:40 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public enum EnterpriseAuthGrade {
	// 0：待认证；1：认证中；2：认证失败；3：已认证
	AUTH_WAIT("0", "待认证"), AUTH_PROCESS("1", "认证中"), AUTH_FAIL("2", "认证失败"), AUTH_SUCCESS("3", "已认证");

	private String code;
	private String desc;

	EnterpriseAuthGrade(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 通过code获取enum对象
	 * 
	 * @param code
	 * @return
	 */
	public static EnterpriseAuthGrade getEnum(String code) {
		for (EnterpriseAuthGrade item : values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}

}
