package com.winter.utils;

import java.util.UUID;

/**
 * Created By Donghua.Chen on 2018/1/9
 */
public class StringUtils {

	public static String UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isEmpty(String str) {
		return org.springframework.util.StringUtils.isEmpty(str);
	}

	/**
	 * 判断是否为空 xuzhaojie -2016-9-13 上午9:56:52
	 */
	public static boolean isNull(Object... object) {
		for (Object obj : object) {
			if (obj == null || "".equals(obj.toString()))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否为非空 xuzhaojie -2016-9-13 上午9:56:52
	 */
	public static boolean isNotNull(Object... object) {
		return !isNull(object);
	}

}
