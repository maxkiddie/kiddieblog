/**
 * 
 */
package com.winter.service.redis;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:50:56
 */
public interface RedisService {
	void setString(String key, String value);

	void setString(String key, String value, Integer second);

	String getString(String key);

}
