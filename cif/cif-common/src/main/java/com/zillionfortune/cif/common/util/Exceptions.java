package com.zillionfortune.cif.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception帮助类
 * 
 * @author litaiping
 * @version  Exceptions.java, v 0.1 2016-7-27 下午3:55:25
 */
public class Exceptions {

    private static final int ERROR_MESSAGE_LENGTH = 600;

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex;
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    /**
     * 将系统异常对象转字符串
     * 
     * @param e
     *            异常对象
     * @return
     */
    public static String exceptionToString(Exception e) {
        StringWriter w = new StringWriter();
        e.printStackTrace(new PrintWriter(w));
        String msg = w.toString();
        if (ERROR_MESSAGE_LENGTH < msg.length()) {
            return msg.substring(0, ERROR_MESSAGE_LENGTH);
        } else {
            return w.toString();
        }

    }
}
