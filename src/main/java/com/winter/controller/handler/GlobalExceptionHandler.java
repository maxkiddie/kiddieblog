/**
 * 
 */
package com.winter.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.exception.TokenEmptyException;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午8:58:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { TokenExpiredException.class, JWTDecodeException.class, TokenEmptyException.class })
	@ResponseBody
	public Result tokenExceptionHandler(HttpServletRequest req, Exception e) {
		String requestURI = req.getRequestURI();
		String return_s = requestURI + " is error :" + e.getMessage();
		log.error(return_s);
		Result result = new Result();
		if (e instanceof TokenExpiredException) {
			result.setResultEnum(ResultEnum.TOKEN_EXPIRE);
		} else if (e instanceof JWTDecodeException) {
			result.setResultEnum(ResultEnum.TOKEN_ERROR);
		} else {
			result.setResultEnum(ResultEnum.TOKEN_EMPTY);
		}
		return result;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result exceptionHandler(HttpServletRequest req, Exception e) {
		String requestURI = req.getRequestURI();
		String return_s = requestURI + " is error :" + e.getMessage();
		log.error(return_s);
		Result result = new Result();
		result.setResultEnum(ResultEnum.FAIL);
		result.setData(e.getMessage());
		return result;
	}
}
