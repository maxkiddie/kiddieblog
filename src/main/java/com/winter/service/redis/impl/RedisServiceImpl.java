/**
 * 
 */
package com.winter.service.redis.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.winter.service.redis.RedisService;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void setString(String key, String value) {
		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
		cliet.set(key, value);
	}

	public void setString(String key, String value, Integer second) {
		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
		cliet.set(key, value, second, TimeUnit.SECONDS);
	}

	public String getString(String key) {
		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
		return cliet.get(key);
	}
}
