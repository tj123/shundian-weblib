package com.shundian.weblib.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 顺点日期对象 2015年10月30日 14:19:36
 * 
 * @author TJ
 */
public class Date2 extends Date{

	private static final long serialVersionUID = 1L;

	/**
	 * 分
	 */
	public static final int MINUTE = Calendar.MINUTE;
	/**
	 * 小时
	 */
	public static final int HOUR = Calendar.HOUR;
	/**
	 * 天
	 */
	public static final int DAY = Calendar.DATE;
	/**
	 * 月
	 */
	public static final int MONTH = Calendar.MONTH;
	/**
	 * 年
	 */
	public static final int YEAR = Calendar.YEAR;

	/**
	 * 所有备选的日期格式 可自行定义
	 */
	/**
	 * 2015-10-30
	 */
	public static final String YYYYMMDD = "yyyy-MM-dd";
	/**
	 * 2015-10-30 17:19
	 */
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	/**
	 * 2015-10-30 17:19:58
	 */
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 20151030171958
	 */
	public static final String YYYYMMDDHHMMSS_EX = "yyyyMMddHHmmss";
	/**
	 * 2015年10月30日
	 */
	public static final String YYYYMMDD_CN = "yyyy年MM月dd日";
	/**
	 * 2015年10月30日 17时20分
	 */
	public static final String YYYYMMDDHHMM_CN = "yyyy年MM月dd日HH时mm分";
	/**
	 * 2015年10月30日 17时20分58秒
	 */
	public static final String YYYYMMDDHHMMSS_CN = "yyyy年MM月dd日HH时mm分ss秒";
	/**
	 * 30日17:28
	 */
	public static final String DDHHMM_CN = "dd日HH:mm";
	/**
	 *17:22:17 
	 */
	public static final String HHMMSS = "HH:mm:ss";
	/**
	 *17:22 
	 */
	public static final String HHMM = "HH:mm";
	
//	private Date date;
	private Calendar calendar;
	private SimpleDateFormat formatter;
	private String pattern = YYYYMMDDHHMMSS;

	public Date2() {
	}

	/**
	 * 添加的时间
	 * 
	 * @param days
	 */
	public Date2(int days) {
		addDay(days);
	}

	/**
	 * @param days 添加的时间
	 * @param pattern 格式
	 */
	public Date2(int days, String pattern) {
		setPattern(pattern);
	}

	/**
	 * 日期格式
	 * @param pattern
	 * @throws ParseException 
	 */
	public Date2(String date) throws ParseException {
		parse(date, pattern);
	}

	/**
	 *  
	 * @param date 字符串日期
	 * @param pattern 日期的格式
	 * @throws ParseException
	 */
	public Date2(String date, String pattern) throws ParseException {
		parse(date, pattern);
	}

	/**
	 * 在需要使用时才初始化 节省内存
	 * 
	 * @return
	 */
	private Calendar getCalendar() {
		if (calendar == null) {
			synchronized (this) {
				calendar = Calendar.getInstance();
				calendar.setTime(this);
			}
		}
		return calendar;
	}

	/**
	 * 在需要使用时才初始化 节省内存
	 * 
	 * @return
	 */
	private SimpleDateFormat getFormatter() {
		if (formatter == null) {
			synchronized (this) {
				formatter = new SimpleDateFormat();
			}
		}
		return formatter;
	}


	/**
	 * 设置它的格式 建议调用时使用 SDate。提示
	 * 
	 * @param pattern
	 * @return
	 */
	public Date2 setPattern(String pattern) {
		this.pattern = pattern;
		return this;
	}

	/**
	 * 添加时间 可选 分 小时 天 月 年
	 * 
	 * @param field
	 * @param amount
	 * @return
	 */
	public Date2 add(int field, int amount) {
		getCalendar().add(field, amount);
		setTime(getCalendar().getTime().getTime());
		return this;
	}

	/**
	 * 添加分
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addMinute(int amount) {
		return add(MINUTE, amount);
	}

	/**
	 * 添加小时
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addHour(int amount) {
		return add(HOUR, amount);
	}

	/**
	 * 添加天
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addDay(int amount) {
		return add(DAY, amount);
	}

	/**
	 * 添加月
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addMonth(int amount) {
		return add(MONTH, amount);
	}
	
	/**
	 * 添加周
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addWeek(int amount) {
		return addDay(amount*7);
	}

	/**
	 * 添加年
	 * 
	 * @param amount
	 * @return
	 */
	public Date2 addYear(int amount) {
		return add(YEAR, amount);
	}
	
	/**
	 * 解析日期
	 * 这里的格式不会修改 默认的格式
	 * @param date
	 *            时间
	 * @param pattern
	 *            格式
	 * @return
	 * @throws ParseException
	 */
	public Date2 parse(String date, String pattern) throws ParseException {
		getFormatter().applyPattern(pattern);
		setTime(getFormatter().parse(date).getTime());
		return this;
	}

	/**
	 * 获取当天是星期几 0为 星期天 星期几 即为几
	 * 
	 * @return
	 */
	public int getWeek() {
		return getCalendar().get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 
	 * @param inChinese
	 * @return
	 */
	public String getWeekCN() {
		String[] weks = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		return "星期" + weks[getWeek()];
	}

	/**
	 * 是否为周末
	 * @return
	 */
	public boolean isWeekend() {
		int d = getWeek();
		return d == 6 || d == 0;
	}

	/**
	 * 是否为工作日
	 * @return
	 */
	public boolean isWeekday(){
		return !isWeekend();
	}
	
	/**
	 * get format toString 都是同一个方法
	 * 
	 * @param pattern
	 *            输入的格式
	 * @return 输出的格式
	 */
	public String get(String pattern) {
		return toString(pattern);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public String get() {
		return toString();
	}

	/**
	 * 获取时间
	 * 
	 * @param pattern
	 * @return
	 */
	public String format(String pattern) {
		return toString(pattern);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public String format() {
		return toString();
	}

	/**
	 * 计算两个日期相差的天数 
	 * @param date 使用 date - 当前日期
	 * @return
	 */
	public int byDays(Date2 date){
		return Math.round((date.getTime() - this.getTime())/(1000*3600*24));
	}
	
	/**
	 * 获取时间
	 * 
	 * @param pattern
	 * @return
	 */
	public String toString(String pattern) {
		setPattern(pattern);
		return toString();
	}

	@Override
	public String toString() {
		getFormatter().applyPattern(pattern);
		return getFormatter().format(this);
	}


	
}
