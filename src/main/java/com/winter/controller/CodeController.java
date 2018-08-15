/**
 * 
 */
package com.winter.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.User;
import com.winter.service.redis.RedisService;
import com.winter.utils.StringUtils;
import com.winter.utils.VelidateImageUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午3:52:10
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController {

	@Value("${code_timeout}")
	private Integer second;
	@Autowired
	private RedisService redisService;

	@Value("${code_prefix}")
	private String CODE_PREFIX;

	@RequestMapping("/imageCode")
	public void imageCode(String sign, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = VelidateImageUtil.generateVerifyCode(4);
		if (!StringUtils.isEmpty(sign))
			redisService.setString(CODE_PREFIX + sign, verifyCode, second);
		// 生成图片
		try {
			VelidateImageUtil.outputImage(response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/sign", produces = { "application/json;charset=UTF-8" })
	public Result sign(User user) {
		Result result = new Result();
		result.setData(StringUtils.UUID());
		return result;
	}

}
