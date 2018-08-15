/**
 * 
 */
package com.winter.base;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午10:15:53
 */
public class Result {
	private Integer code;
	private String msg;
	private Object data;

	/**
	 * @param code
	 */
	public Result(ResultEnum resultEnum) {
		super();
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	public Result() {
		super();
		this.code = ResultEnum.SUSS.getCode();
		this.msg = ResultEnum.SUSS.getMsg();
	}

	public void setResultEnum(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
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

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
