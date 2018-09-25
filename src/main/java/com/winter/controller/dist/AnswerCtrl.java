package com.winter.controller.dist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.Answer;
import com.winter.service.answer.AnswerService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/distApi/answer")
public class AnswerCtrl {

	@Autowired
	private AnswerService answerService;

	@ResponseBody
	@RequestMapping(value = "/save", produces = { "application/json;charset=UTF-8" })
	public Result save(Answer answer, String token) {
		return answerService.saveOrUpdateAnswer(token, answer);
	}

	@ResponseBody
	@RequestMapping(value = "/delete", produces = { "application/json;charset=UTF-8" })
	public Result login(Integer id, String token) {
		return answerService.deleteAnswer(token, id);
	}

}
