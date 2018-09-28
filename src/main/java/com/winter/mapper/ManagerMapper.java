package com.winter.mapper;

import com.winter.mapper.base.BaseMapper;
import com.winter.model.Manager;

public interface ManagerMapper extends BaseMapper<Manager> {
	Manager selectManagerByUserNameAndPwd(Manager manager);
}