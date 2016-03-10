package com.shundian.test;

import java.util.Date;

import org.junit.Test;

import com.shundian.weblib.bean.BeanConvertException;
import com.shundian.weblib.bean.validate.NotValidException;

public class BeanTest {

	@Test
	public void test(){
		UserDto dto = new UserDto();
		try {
			dto.validate();
		} catch (NotValidException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tesst(){
		UserDto dto = new UserDto();
		try {
			dto.validate(true);
		} catch (NotValidException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		UserDto dto = new UserDto();
		try {
			dto.setPhone("13689005323");
			dto.replaceNull();
			User user = null;
			dto.setBirthday("2015-11-25 12:23:00");
			dto.setComment("eeaadd");
			dto.setAge("1w2");
			dto.setWeight("2.4");
			dto.setDate(new Date());
			dto.validate();
			user = dto.toPo(User.class);
			
			System.out.println(user);
		} catch (BeanConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotValidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
