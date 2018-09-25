package com.winter.service.user.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.mapper.UserMapper;
import com.winter.model.User;
import com.winter.service.redis.RedisService;
import com.winter.service.user.UserService;
import com.winter.utils.Md5Util;
import com.winter.utils.StringUtils;
import com.winter.utils.TokenUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Value("${HEAD_URL}")
	private String HEAD_URL;
	@Value("${code_prefix}")
	private String CODE_PREFIX;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisService redisService;

	/**
	 * 用户注册
	 */
	public Result addUser(User user, String sign, String code) {
		Result result = new Result();
		// 验证码
		if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(code)) {
			log.error("验证码或者标识不能为空");
			result.setResultEnum(ResultEnum.CODE_ERROR);
			return result;
		}
		String checkCode = redisService.getString(CODE_PREFIX + sign);
		if (StringUtils.isEmpty(checkCode)) {
			log.error("验证码超时");
			result.setResultEnum(ResultEnum.CODE_TIMEOUT);
			return result;
		}
		if (code.equals(checkCode)) {
			log.error("验证码不匹配,传输code：" + code + ",内存code:" + checkCode);
			result.setResultEnum(ResultEnum.CODE_NOT_MATCH);
			return result;
		}
		user.setPassword(Md5Util.getMD5(user.getPassword()));
		User record = new User(user.getUserName());
		record = userMapper.selectOne(record);
		if (record != null) {// 用户名存在
			result.setResultEnum(ResultEnum.USER_EXSIT);
			return result;
		}
		int flag = 0;
		try {
			Date now = new Date();
			user.setHeadUrl(HEAD_URL);// 设置默认头像
			user.setRegisterDate(now);// 注册时间
			user.setLastLoginTime(now);// 登录时间
			user.setIsBlack(0);// 白名单
			flag = userMapper.insert(user);
		} catch (Exception e) {
			log.error("添加用户失败:" + user.getNickName());
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		if (flag != 0) {
			log.info("添加用户成功:" + user.getNickName());
		}
		return result;
	}

	/**
	 * 用户登录
	 */
	public Result login(User user, String sign, String code) {
		Result result = new Result();
		// 验证码
		if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(code)) {
			log.error("验证码或者标识不能为空");
			result.setResultEnum(ResultEnum.CODE_ERROR);
			return result;
		}
		String checkCode = redisService.getString(CODE_PREFIX + sign);
		if (StringUtils.isEmpty(checkCode)) {
			log.error("验证码超时");
			result.setResultEnum(ResultEnum.CODE_TIMEOUT);
			return result;
		}
		if (code.equals(checkCode)) {
			log.error("验证码不匹配,传输code：" + code + ",内存code:" + checkCode);
			result.setResultEnum(ResultEnum.CODE_NOT_MATCH);
			return result;
		}
		user.setPassword(Md5Util.getMD5(user.getPassword()));
		User record = new User(user.getUserName(), user.getPassword());
		record.setIsBlack(0);// 白名单条件
		if (StringUtils.isNull(record.getUserName(), record.getPassword())) {
			result.setResultEnum(ResultEnum.PARAMS_ERROR);
			return result;
		}
		record = userMapper.selectUserByUserNameAndPwd(record);
		if (record != null) {// 用户名匹配成功
			result.setResultEnum(ResultEnum.SUSS);
			result.setData(TokenUtil.createToken(record.getId()));
			log.info("用户登录成功:" + record.getUserName());
		} else {
			result.setResultEnum(ResultEnum.USER_NO_MATCH);
			log.error("用户登录失败:" + user.getUserName());
		}
		return result;
	}

	/**
	 * 获取用户信息
	 */
	public Result getUserInfo(String token) {
		Integer id = TokenUtil.getUserId(token);
		User user = userMapper.selectByPrimaryKey(id);
		Result result = new Result();
		if (user == null) {
			result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
		} else {
			result.setResultEnum(ResultEnum.SUSS);
			result.setData(user);
		}
		return result;
	}

	public Result findAllUser() {
		Result result = new Result();
		List<User> list = userMapper.selectAll();
		result.setData(list);
		return result;
	}

}
