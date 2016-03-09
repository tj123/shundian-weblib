package com.shundian.test;

import java.lang.reflect.Field;

import com.shundian.weblib.bean.validate.Checkable;
import com.shundian.weblib.bean.validate.NotValidException;

public class CheckString implements Checkable{

	@Override
	public void check(Object value) throws NotValidException {
		System.out.println(value);
	}






}
