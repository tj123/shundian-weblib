package com.shundian.weblib.bean.validate;

/**
 * 包裹错误的对象
 * @author TJ
 *
 */
public class Error {

	public Error() {
	}

	public Error(String name, String message) {
		this.name = name;
		this.message = message;
	}

	/**
	 * 错误字段名称
	 */
	private String name;
	/**
	 * 错误字段内容
	 */
	private String message;

	public String getName() {
		return name;
	}

	public Error setName(String name) {
		this.name = name;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Error setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return "Error [name=" + name + ", message=" + message + "]";
	}
	
	

}
