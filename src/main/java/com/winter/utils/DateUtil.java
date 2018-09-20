/**
 * 
 */
package com.winter.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author xuzhaojie
 *
 *         2018年9月20日 上午10:24:20
 */
public class DateUtil {
	public final static String pattern_full = "yyyy-MM-dd HH:mm:ss";
	public final static String pattern_date = "yyyy-MM-dd";

	private static final ThreadLocal<DateFormat> df_full = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_full);// 常用的一种类型
		}
	};

	private static final ThreadLocal<DateFormat> df_date = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_date);// 常用的一种类型
		}
	};

	private static final DateFormat getDateFormat(ThreadLocal<DateFormat> format) {
		return (DateFormat) format.get();
	}

	// 转化数据类型,string->date
	public static Date getDateByDateFormat(String date_str, DateFormat df) {
		if (StringUtils.isEmpty(date_str))
			return null;
		try {
			// 转化数据格式
			return df.parse(date_str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// date->string
	public static String getDateStrByDateFormat(Date date, DateFormat df) {
		return df.format(date);
	}

	// 获取当前Date
	public static Date getCurrentDate() {
		Instant instant = Instant.now();
		return Date.from(instant);
	}

	// 获取DateStr[yyyy-MM-dd HH:mm:ss]
	public static String getCurrentTimeFullStr() {
		return getDateStrByDateFormat(getCurrentDate(), getDateFormat(df_full));
	}

	public static String getTodatDate() {
		DateFormat df = df_date.get();
		return df.format(new Date());
	}

	public static void main(String[] args) {
		System.err.println(getTodatDate().replace("-", "/"));
	}
}
