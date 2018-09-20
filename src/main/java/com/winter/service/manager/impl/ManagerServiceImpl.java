package com.winter.service.manager.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.mapper.ManagerMapper;
import com.winter.model.Manager;
import com.winter.service.manager.ManagerService;
import com.winter.utils.Md5Util;
import com.winter.utils.TokenUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	private final static Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private ManagerMapper managerMapper;

	/**
	 * 管理员注册
	 */
	public Result saveOrUpdate(Manager manager, String comfirmPwd) {
		Result result = new Result();
		manager.setPassword(Md5Util.getMD5(manager.getPassword()));
		String userName = manager.getUserName();
		int flag = 0;
		try {
			if (manager.getId() == null) {
				manager.setCreateTime(new Date());
				manager.setStatus(0);
				Manager record = new Manager();
				record.setUserName(manager.getUserName());
				record = managerMapper.selectOne(record);
				if (record != null) {// 用户名存在
					result.setResultEnum(ResultEnum.USER_EXSIT);
					return result;
				}
				if (!manager.getPassword().equals(Md5Util.getMD5(comfirmPwd))) {
					result.setResultEnum(ResultEnum.COMFIRM_PWD_NO_MATCH);
					log.info("两次密码输入不一致:" + manager.getUserName());
					return result;
				}
				flag = managerMapper.insert(manager);
			} else {
				manager.setUserName(null);
				flag = managerMapper.updateByPrimaryKeySelective(manager);
			}
		} catch (Exception e) {
			log.error("更改管理员失败:" + userName);
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		if (flag != 0) {
			log.info("更改管理员信息成功:" + userName);
		}
		return result;
	}

	/**
	 * 管理员登录
	 */
	public Result login(Manager manager) {
		Result result = new Result();
		manager.setPassword(Md5Util.getMD5(manager.getPassword()));
		Manager record = new Manager(manager.getUserName(), manager.getPassword());
		record = managerMapper.selectOne(record);
		if (record != null) {// 管理员名匹配成功
			result.setResultEnum(ResultEnum.SUSS);
			result.setData(TokenUtil.createToken(record.getId()));
			log.info("管理员登录成功:" + record.getUserName());
		} else {
			result.setResultEnum(ResultEnum.USER_NO_MATCH);
			log.error("管理员登录失败:" + manager.getUserName());
		}
		return result;
	}

	/**
	 * 获取管理员信息
	 */
	public Result getManagerInfo(String token) {
		Integer id = TokenUtil.getUserId(token);
		Manager manager = managerMapper.selectByPrimaryKey(id);
		Result result = new Result();
		if (manager == null) {
			result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
		} else {
			result.setResultEnum(ResultEnum.SUSS);
			result.setData(manager);
		}
		return result;
	}

	public Result findAllManager() {
		Result result = new Result();
		List<Manager> list = managerMapper.selectAll();
		result.setData(list);
		return result;
	}

}
