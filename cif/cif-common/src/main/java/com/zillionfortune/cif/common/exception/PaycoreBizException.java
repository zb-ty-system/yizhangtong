package com.zillionfortune.cif.common.exception;


/**
 * 业务异常类
 * 
 * @author litaiping
 * @version  PaycoreBizException.java, v 0.1 2016-7-27 上午9:32:42
 */
public class PaycoreBizException extends PaycoreException {

    private static final long serialVersionUID = 465700048769794514L;

    public PaycoreBizException(String paramString, Throwable paramThrowable, String errorCode, String errorMsg) {
        super(paramString, paramThrowable, errorCode, errorMsg);
    }

    public String toString() {
        return getErrorCode() + getErrorMsg();
    }
}
