/**
 * 
 */
package com.winter.base;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午10:18:09
 */
public enum ResultEnum {
	SUSS(0, "成功"), FAIL(1, "系统错误"), DB_FAIL(2, "数据库出现问题"), PARAMS_ERROR(3, "传入参数错误"), DATA_NOT_FOUND(4,
			"找不到相关数据"), TOKEN_ERROR(10, "token错误"), TOKEN_EXPIRE(11, "token已过期"), TOKEN_EMPTY(12,
					"token为空,用户请登录"), CODE_ERROR(13, "验证码或者标识不能为空"), CODE_TIMEOUT(14,
							"验证码超时"), CODE_NOT_MATCH(15, "验证码不匹配"), USER_EXSIT(20, "用户名已存在"), USER_NO_MATCH(21,
									"用户名或者密码错误"), COMFIRM_PWD_NO_MATCH(22, "两次密码不一致"), ANSWER_MODIFY_AUTH(30,
											"没有修改回答所属权限"), QUESTION_MODIFY_AUTH(31, "没有修改问题所属权限"), FILE_UPLOAD_ERROR(40,
													"文件上传失败"), FILE_FORMAT_ERROR(41, "文件上传类型错误");
	private Integer code;
	private String msg;

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
