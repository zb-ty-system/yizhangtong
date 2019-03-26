package com.zillionfortune.cif.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	
	private static Random rand = new Random();
	
	public static Integer[] randomIntArray(){
		Integer[] intRet = new Integer[6];
		int intRd = 0;
		int count = 0;
		int flag = 0;
		while (count < 6) {
			intRd = (int) Math.round(Math.random()*10+5);
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd || intRet[i] == (intRd+1)) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				count++;
			}
		}
		Arrays.sort(intRet);
		return intRet;
	}
	
    public static String generateRandomNumber(String src) {
    	if("".equals(src.trim())){
    		return src;
    	}
    	char[] list =src.toCharArray();
        for(int i = list.length; i > 1; i--){
            //从0到i之间随机取一个值，跟i处的元素交换
           swap(list,i-1,rand.nextInt(i));
        }
    	return String.valueOf(list);
    }

    /**
     * 指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
    
    private static void swap(char[] list, int i, int j) {
        char temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
	
    /** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    /** 
     * 电话号码验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }  
    
    /*********************************** 身份证验证开始 ****************************************/    
    /** 
     * 身份证号码验证  
     * 1、号码的结构 
     * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码， 
     * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 
     * 2、地址码(前六位数）  
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。  
     * 3、出生日期码（第七位至十四位） 
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。  
     * 4、顺序码（第十五位至十七位） 
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 
     * 顺序码的奇数分配给男性，偶数分配给女性。  
     * 5、校验码（第十八位数） 
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 
     * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 
     * X 9 8 7 6 5 4 3 2 
     */  
  
    /** 
     * 功能：身份证的有效验证 
     * @param IDStr 身份证号 
     * @return 有效：返回"" 无效：返回String信息 
     * @throws ParseException 
     */  
    public static String IDCardValidate(String IDStr) throws ParseException {  
        String errorInfo = "";// 记录错误信息  
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",  
                "3", "2" };  
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",  
                "9", "10", "5", "8", "4", "2" };  
        String Ai = "";  
        // ================ 号码的长度 15位或18位 ================  
        if (IDStr.length() != 15 && IDStr.length() != 18) {  
            errorInfo = "身份证号码长度应该为15位或18位";  
            return errorInfo;  
        }  
        // =======================(end)========================  
  
        // ================ 数字 除最后以为都为数字 ================  
        if (IDStr.length() == 18) {  
            Ai = IDStr.substring(0, 17);  
        } else if (IDStr.length() == 15) {  
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);  
        }  
        if (isNumeric(Ai) == false) {  
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字";  
            return errorInfo;  
        }  
        // =======================(end)========================  
  
        // ================ 出生年月是否有效 ================  
        String strYear = Ai.substring(6, 10);// 年份  
        String strMonth = Ai.substring(10, 12);// 月份  
        String strDay = Ai.substring(12, 14);// 月份  
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {  
            errorInfo = "身份证生日无效";  
            return errorInfo;  
        }  
        GregorianCalendar gc = new GregorianCalendar();  
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150  
                || (gc.getTime().getTime() - s.parse(  
                        strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
            errorInfo = "身份证生日不在有效范围";  
            return errorInfo;  
        }  
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {  
            errorInfo = "身份证月份无效";  
            return errorInfo;  
        }  
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {  
            errorInfo = "身份证日期无效";  
            return errorInfo;  
        }  
        // =====================(end)=====================  
  
        // ================ 地区码时候有效 ================  
        ConcurrentHashMap<String, String> h = GetAreaCode();  
        if (h.get(Ai.substring(0, 2)) == null) {  
            errorInfo = "身份证地区编码错误";  
            return errorInfo;  
        }  
        // ==============================================  
  
        // ================ 判断最后一位的值 ================  
        int TotalmulAiWi = 0;  
        for (int i = 0; i < 17; i++) {  
            TotalmulAiWi = TotalmulAiWi  
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))  
                    * Integer.parseInt(Wi[i]);  
        }  
        int modValue = TotalmulAiWi % 11;  
        String strVerifyCode = ValCodeArr[modValue];  
        Ai = Ai + strVerifyCode;  
  
        if (IDStr.length() == 18) {  
            if (Ai.equals(IDStr) == false) {  
                errorInfo = "身份证无效，不是合法的身份证号码";  
                return errorInfo;  
            }  
        } else {  
            return "TRUE"; 
        }  
        // =====================(end)=====================  
        return "TRUE";  
    } 
    /** 
     * 功能：设置地区编码 
     * @return ConcurrentHashMap 对象 
     */  
    private static  ConcurrentHashMap<String, String> GetAreaCode() {  
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();  
        map.put("11", "北京");  
        map.put("12", "天津");  
        map.put("13", "河北");  
        map.put("14", "山西");  
        map.put("15", "内蒙古");  
        map.put("21", "辽宁");  
        map.put("22", "吉林");  
        map.put("23", "黑龙江");  
        map.put("31", "上海");  
        map.put("32", "江苏");  
        map.put("33", "浙江");  
        map.put("34", "安徽");  
        map.put("35", "福建");  
        map.put("36", "江西");  
        map.put("37", "山东");  
        map.put("41", "河南");  
        map.put("42", "湖北");  
        map.put("43", "湖南");  
        map.put("44", "广东");  
        map.put("45", "广西");  
        map.put("46", "海南");  
        map.put("50", "重庆");  
        map.put("51", "四川");  
        map.put("52", "贵州");  
        map.put("53", "云南");  
        map.put("54", "西藏");  
        map.put("61", "陕西");  
        map.put("62", "甘肃");  
        map.put("63", "青海");  
        map.put("64", "宁夏");  
        map.put("65", "新疆");  
        map.put("71", "台湾");  
        map.put("81", "香港");  
        map.put("82", "澳门");  
        map.put("91", "国外");  
        return map;  
    }  
    
    /** 
     * 功能：判断字符串是否为数字 
     * @param str 
     * @return 
     */  
    public static boolean isNumeric(String str) {  
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(str);  
        if (isNum.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * 功能：判断字符串是否为日期格式 
     * @param str 
     * @return 
     */  
    public static boolean isDate(String strDate) {  
        Pattern pattern = Pattern  
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");  
        Matcher m = pattern.matcher(strDate);  
        if (m.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
}
