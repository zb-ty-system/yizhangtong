package com.zillionfortune.cif.common.enums;

/**
 * ClassName: AuthGrade <br/>
 * Function: 个人会员等级类型. <br/>
 * Date: 2016年11月22日 上午10:21:40 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public enum PersonGradeType {

	AUTH(1, "认证"),
	// TODO:待确认
	TYPE2(2, ""),TYPE3(3, ""),TYPE4(4, ""),TYPE5(5, ""),TYPE6(6, ""),TYPE7(7, ""),TYPE8(8, "");

	private int code;
	private String desc;

	PersonGradeType(int code, String desc) {
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
	public static PersonGradeType getEnum(int code) {
		for (PersonGradeType item : values()) {
			if (item.getCode() == code) {
				return item;
			}
		}
		return null;
	}

}
