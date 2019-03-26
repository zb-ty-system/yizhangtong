package com.zillionfortune.cif.common.enums;

/**
 * ClassName: AuthGrade <br/>
 * Function: 企业会员等级类型. <br/>
 * Date: 2016年11月22日 上午10:21:40 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public enum EnterpriseGradeType {

	AUTH(1, "认证"),
	// TODO:待确认
	RISK(2, "风险"),TYPE3(3, ""),TYPE4(4, ""),TYPE5(5, ""),TYPE6(6, ""),TYPE7(7, ""),TYPE8(8, "");

	private int code;
	private String desc;

	EnterpriseGradeType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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
	public static EnterpriseGradeType getEnum(int code) {
		for (EnterpriseGradeType item : values()) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return null;
	}

}
