package com.shundian.weblib.bean;

/**
 * po 转换时的错误
 * @author TJ
 *
 */
public class BeanConvertException extends Exception {

	private static final long serialVersionUID = 1L;

	public BeanConvertException() {
		this(BeanConvertUtil.DEFALUT_ERROR);
	}

	public BeanConvertException(String message) {
		super(message);
	}
	
}
