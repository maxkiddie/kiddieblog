package com.winter.service.question;

import com.winter.base.Result;
import com.winter.model.Question;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:50:56
 */
public interface QuestionService {

	Result saveOrUpdateQuestion(String token, Question question);

	Result deleteQuestion(String token, Integer id);
}
