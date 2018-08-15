package com.winter.service.user;

import com.winter.base.Result;
import com.winter.model.User;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:50:56
 */
public interface UserService {

	Result addUser(User user, String sign, String code);

	Result login(User user);

	Result getUserInfo(String token);

	Result findAllUser();
}
