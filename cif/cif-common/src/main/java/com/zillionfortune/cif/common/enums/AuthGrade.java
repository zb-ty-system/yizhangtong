package com.zillionfortune.cif.common.enums;

/**
 * ClassName: AuthGrade <br/>
 * Function: 个人会员认证类型. <br/>
 * Date: 2016年11月22日 上午10:21:40 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public enum AuthGrade {

	UNAUTHORIZED("0", "未认证"), AUTHENTICATED("1", "已认证"), STRONG_AUTH("2", "强认证");

	private String code;
	private String desc;

	AuthGrade(String code, String desc) {
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
	public static AuthGrade getEnum(String code) {
		for (AuthGrade item : values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}

}
