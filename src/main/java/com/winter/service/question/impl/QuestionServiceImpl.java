package com.winter.service.question.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.mapper.QuestionMapper;
import com.winter.model.Question;
import com.winter.service.question.QuestionService;
import com.winter.utils.TokenUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	private QuestionMapper questionMapper;

	public Result saveOrUpdateQuestion(String token, Question question) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		Integer userId = TokenUtil.getUserId(token);
		try {
			if (StringUtils.isEmpty(question.getId())) {
				question.setUserId(userId);// 设置问题所属用户id
				question.setCreateTime(new Date());
				flag = questionMapper.insertSelective(question);
			} else {// 保存修改
				Question tempQuestion = questionMapper.selectByPrimaryKey(question.getId());
				if (tempQuestion != null) {// 判断该id的问题是否存在
					if (tempQuestion.getUserId().equals(userId)) {// 判断是否所属用户的问题
						// 修改保存
						question.setModifyTime(new Date());
						flag = questionMapper.updateByPrimaryKeySelective(question);
					} else {
						log.error("没有修改该问题的权限");
						result.setResultEnum(ResultEnum.QUESTION_MODIFY_AUTH);
					}
				} else {
					log.error("没有找到该问题数据");
					result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
				}
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("保存问题成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("保存问题失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

	public Result deleteQuestion(String token, Integer id) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		Integer userId = TokenUtil.getUserId(token);
		try {
			Question tempQuestion = questionMapper.selectByPrimaryKey(id);
			if (tempQuestion != null) {// 判断该id的问题是否存在
				if (tempQuestion.getUserId().equals(userId)) {// 判断是否所属用户的问题
					// 删除问题
					flag = questionMapper.deleteByPrimaryKey(id);
				} else {
					log.error("没有删除该问题的权限");
					result.setResultEnum(ResultEnum.QUESTION_MODIFY_AUTH);
				}
			} else {
				log.error("没有找到该问题数据");
				result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("问题删除:" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除问题失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

}
