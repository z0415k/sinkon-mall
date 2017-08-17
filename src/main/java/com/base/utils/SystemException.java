package com.base.utils;

/*
 * 由于系统异常或程序错误导致的异常，当发生这种异常时，用户无法干预，只能由程序员处理。
 * 展现层只提示用户发生，系统异常，不显示详细信息。
 * 
 */
public class SystemException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    /**
     * 缺省构造方法
     */
    public SystemException() {
	/* empty */
    }
    /**
     * 有消息参数的构造方法
     * @param msg
     */
    public SystemException(String msg) {
    	super(msg);
    }
    /**
     * 有消息，可抛出异常做参数的构造方法
     * @param msg
     * @param e
     */
    public SystemException(String msg, Throwable e) {
    	super(msg, e);
    }
    /**
     * 有可抛出异常做参数的构造方法
     * @param e
     */
    public SystemException(Throwable e) {
    	super(e);
    }
}
