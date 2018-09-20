package com.winter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.Manager;
import com.winter.service.manager.ManagerService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@ResponseBody
	@RequestMapping(value = "/save", produces = { "application/json;charset=UTF-8" })
	public Result save(Manager manager, String comfirmPwd) {
		return managerService.saveOrUpdate(manager,comfirmPwd);
	}

	@ResponseBody
	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8" })
	public Result login(Manager manager) {
		return managerService.login(manager);
	}

	@ResponseBody
	@RequestMapping(value = "/getManagerInfo", produces = { "application/json;charset=UTF-8" })
	public Result getManagerInfo(String token) {
		return managerService.getManagerInfo(token);
	}

	@ResponseBody
	@RequestMapping(value = "/all", produces = { "application/json;charset=UTF-8" })
	public Result findAllManager() {
		return managerService.findAllManager();
	}
}
