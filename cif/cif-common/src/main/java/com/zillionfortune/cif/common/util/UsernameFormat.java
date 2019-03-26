package com.zillionfortune.cif.common.util;
/**
 * UsernameFormat
 * 
 * @author litaiping
 * @version  UsernameFormat.java, 2016-6-28 下午2:57:06 litaiping
 */
public class UsernameFormat {
	
	/**
	 * 根据新的规则获取用户名的前台显示
	 * 
	 *  1.手机号：沿用原有规则。例：187****0113
	 *	2.邮箱：显示前三位，接三个*，从@开始显示。例：qer***@163.com
	 *	3.用户名：显示前三位，接三个*，显示后两位。Sds***98
	 *
	 * @param username
	 * @return 符合条件的转换
	 */
	public static String formatUsername(String username) {
		
		if (org.apache.commons.lang.StringUtils.isBlank(username)) {
			return "";
		}
		
		// 全部修改为前三后2，中间补足11位
		return username.replaceAll("^(.{3})(.*?)(.{2})$", "$1******$3");
	}
	
	
	public static void main(String[] args) {
		System.out.println(UsernameFormat.formatUsername("18445156590"));
		System.out.println(UsernameFormat.formatUsername("test1233"));
		System.out.println(UsernameFormat.formatUsername("chenminpierre@gmail.com"));
	}
}
