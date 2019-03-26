package com.zb.sms.common.utils;

import java.util.Random;

/**
 * 功能: 动态码工具类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 15:37
 * 版本: V1.0
 */
public class DynamicCodeUtils {

    /**
     * 纯数字集
     */
    private static final String NUMBER_CODE = "1234567890";

    /**
     * 字母集去掉了1,0,i,o几个容易混淆的字符
     */
    private static final String LETTER_CODE = "23456789abcdefghjkmnpqrstuvwxyz";

    /**
     * 生成动态码
     *
     * @param codeLength   验证码长度
     * @param containsChar 是否包含字母
     * @return
     */
    public static String generate(int codeLength, boolean containsChar) {
        StringBuilder dynamicCode = new StringBuilder(codeLength);
        try {
            String sources = containsChar ? LETTER_CODE : NUMBER_CODE;
            int codesLen = sources.length();
            Random rand = new Random(System.currentTimeMillis());
            for (int i = 0; i < codeLength; i++) {
                dynamicCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dynamicCode.toString();
    }
}
