/**
 * 
 */
package com.winter.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.controller.constant.CommonConstant;

/**
 * @author xuzhaojie
 *
 *         2018年9月21日 下午4:19:57
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = { "/distApi/*" }, initParams = {
		@WebInitParam(name = "noToken", value = "/distApi/manager/login") })
public class LoginFilter extends BaseFilter {

	private List<String> noTokenList = new LinkedList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Enumeration<String> list = filterConfig.getInitParameterNames();
		while (list.hasMoreElements()) {
			noTokenList.add(filterConfig.getInitParameter(list.nextElement()));
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		for (String url : noTokenList) {
			if (request.getRequestURI().startsWith(url)) {
				chain.doFilter(request, response);
				return;
			}
		}
		String token = request.getParameter(CommonConstant.TOKEN);
		if (token == null || "".equals(token)) {
			writeResult(new Result(ResultEnum.TOKEN_EMPTY), response);
		} else {
			chain.doFilter(request, response);
		}
	}

}
