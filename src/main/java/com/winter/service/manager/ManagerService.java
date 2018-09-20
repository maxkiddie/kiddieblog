/**
 * 
 */
package com.winter.service.manager;

import com.winter.base.Result;
import com.winter.model.Manager;

/**
 * @author xuzhaojie
 *
 *         2018年9月20日 下午5:06:08
 */
public interface ManagerService {
	Result saveOrUpdate(Manager manager, String confirmPwd);

	Result login(Manager manager);

	Result getManagerInfo(String token);

	Result findAllManager();
}
