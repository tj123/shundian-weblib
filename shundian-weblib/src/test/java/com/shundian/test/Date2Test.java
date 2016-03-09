package com.shundian.test;

import java.text.ParseException;

import com.shundian.weblib.common.Date2;

public class Date2Test {
	
	public static void main(String[] args) {
		try {
			//额额啊啊
			Date2 date = new Date2("2016-01-30","yyyy-MM-dd");//.setPattern("yyyy-MM-dd hh时mm分ss秒").addDay(2).addMinute(1).addWeek(1);
			System.out.println(date.byDays(new Date2("2016-01-30","yyyy-MM-dd").addMonth(12)));
			System.out.println(date.isWeekday());
		} catch (ParseException e) {
			e.printStackTrace();
		}

}

}
