/**
 * 
 */
package com.winter.exception;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午11:54:23
 */
public class TokenEmptyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenEmptyException(String message) {
		this(message, null);
	}

	public TokenEmptyException(String message, Throwable cause) {
		super(message, cause);
	}
}
