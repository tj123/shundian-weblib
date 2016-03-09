package com.shundian.weblib.bean.validate;

/**
 * 校验为空的回掉方法
 * @author TJ
 *
 */
public interface CheckBlankCallBack {
	
	/**
	 * 不为空的处理
	 * @param value 
	 * @throws NotValidException 
	 */
	void onNotBlank(String value) throws NotValidException;
	
	/**
	 * 为空的处理
	 * @throws NotValidException 
	 */
	void onBlank() throws NotValidException;

}
