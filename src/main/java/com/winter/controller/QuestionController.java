package com.winter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.Question;
import com.winter.service.question.QuestionService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@ResponseBody
	@RequestMapping(value = "/save", produces = { "application/json;charset=UTF-8" })
	public Result save(Question question, String token) {
		return questionService.saveOrUpdateQuestion(token, question);
	}

	@ResponseBody
	@RequestMapping(value = "/delete", produces = { "application/json;charset=UTF-8" })
	public Result login(Integer id, String token) {
		return questionService.deleteQuestion(token, id);
	}

}
