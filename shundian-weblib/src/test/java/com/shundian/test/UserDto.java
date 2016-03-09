package com.shundian.test;

import java.util.Date;

import com.shundian.weblib.bean.BaseBean;
import com.shundian.weblib.bean.DatePattern;
import com.shundian.weblib.bean.PoClass;
import com.shundian.weblib.bean.validate.Message;
import com.shundian.weblib.bean.validate.NotNull;
import com.shundian.weblib.bean.validate.Phone;
import com.shundian.weblib.bean.validate.ValidRegExp;
import com.shundian.weblib.bean.validate.Validate;
import com.shundian.weblib.bean.validate.ValidateMethod;

@PoClass(User.class)
@Validate
public class UserDto extends BaseBean{

	private String name;

//	@ValidRegExp(value="\\d+",message="只能位数字")
	private String age;

	private String tel;

	@Message("格式不正确")
	@DatePattern(value = "yyyy-MM-dd hh:mm:ss",message = "呵呵大大")
	private String birthday;
	
	@DatePattern("yyyy-MM-dd hh:mm:ss")
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Message("不符合要求")
	@Phone
	@ValidateMethod(CheckString.class)
	private String phone;

	@Message(" ")
//	@NotNull(message = "我")
	@ValidRegExp(value = "[\\d\\.]+",message="是能为数字")
	private String weight;

	
	@ValidRegExp(value = "\\w+",message = "只能为字符")
//	@NotNull(message = "不能为空")
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
