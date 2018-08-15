/**
 * 
 */
package com.winter.utils;

import com.alibaba.fastjson.JSON;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午11:50:06
 */
public class JsonUtil {

	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}
}
