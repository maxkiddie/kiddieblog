package com.winter.service.answer;

import com.winter.base.Result;
import com.winter.model.Answer;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:50:56
 */
public interface AnswerService {

	Result saveOrUpdateAnswer(String token, Answer answer);

	Result deleteAnswer(String token, Integer id);
}
