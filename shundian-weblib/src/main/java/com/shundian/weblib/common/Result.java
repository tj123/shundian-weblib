package com.shundian.weblib.common;


/**
 * 通过ajax返回的对象
 * @author TJ
 *
 * @param <T>
 */
public class Result<T> {

	public Result() {
	}

	public Result(boolean status) {
		this(status, "");
	}

	public Result(boolean status, String msg) {
		this.status = status;
		this.message = msg;
	}

	private boolean status = false;
	private String message;
	private String backpage;
	private T data;

	public Result<T> setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result<T> ok(String msg) {
		this.setMessage(msg);
		this.setStatus(true);
		return this;
	}

	public Result<T> ok() {
		return ok("");
	}

	public Result<T> error(String msg) {
		this.setMessage(msg);
		this.setStatus(false);
		return this;
	}

	public Result<T> error() {
		return error("");
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

	public Result<T> setBackpage(String backpage) {
		this.backpage = backpage;
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getBackpage() {
		return backpage;
	}

	public T getData() {
		return data;
	}

}