package com.shundian.weblib.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * bean包的工具类
 * 
 * @author TJ
 *
 */
public class BeanConvertUtil {

	private static Log log = LogFactory.getLog(BeanConvertUtil.class);
	
	public static final String PO_NOT_FOUND = "@PoClass not found!";

	public static final String DEFALUT_ERROR = "po convert error!";

	public static final String ASSERT_FAILD = "po class assert faild!";

	public static final String METHOD_NOT_FOUND = "method not found!";

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 未处理的异常
	 */
	public static final String UNDISPOSED_ERROR = "undisposed error!";

	/**
	 * 字符串转 int
	 * 
	 * @param string
	 * @return
	 */
	public static int string2Int(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			if(log.isWarnEnabled()){
				log.warn(string + " parse error!");
			}
		}
		return 0;

	}

	/**
	 * 字符串转 double
	 * 
	 * @param string
	 * @return
	 */
	public static double string2Double(String string) {
		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException e) {
			if(log.isWarnEnabled()){
				log.warn(string + " parse error!");
			}
		}
		return 0;
	}

	/**
	 * 转为 字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String toString(Object o) {
		return String.valueOf(o);
	}

	/**
	 * 字符串转为日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String date, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

}
