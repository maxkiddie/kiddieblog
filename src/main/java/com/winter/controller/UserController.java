package com.winter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.User;
import com.winter.service.user.UserService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/add", produces = { "application/json;charset=UTF-8" })
	public Result addUser(User user, String sign, String code) {
		return userService.addUser(user, sign, code);
	}

	@ResponseBody
	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8" })
	public Result login(User user, String sign, String code) {
		return userService.login(user, sign, code);
	}

	@ResponseBody
	@RequestMapping(value = "/getUserInfo", produces = { "application/json;charset=UTF-8" })
	public Result getUserInfo(String token) {
		return userService.getUserInfo(token);
	}

	@ResponseBody
	@RequestMapping(value = "/all", produces = { "application/json;charset=UTF-8" })
	public Result findAllUser() {
		return userService.findAllUser();
	}
}
