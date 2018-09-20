/**
 * 
 */
package com.winter.service.answer.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.mapper.AnswerMapper;
import com.winter.model.Answer;
import com.winter.service.answer.AnswerService;
import com.winter.utils.TokenUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午2:36:18
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
	private final static Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

	@Autowired
	AnswerMapper answerMapper;

	public Result saveOrUpdateAnswer(String token, Answer answer) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		Integer userId = TokenUtil.getUserId(token);
		try {
			Date now = new Date();
			if (StringUtils.isEmpty(answer.getId())) {
				answer.setUserId(userId);// 设置回答所属用户id
				answer.setAnswerTime(now);// 回答时间
				answer.setIsBlack(0);
				answer.setIsTop(0);
				flag = answerMapper.insertSelective(answer);
			} else {// 保存修改
				Answer tempAnswer = answerMapper.selectByPrimaryKey(answer.getId());
				if (tempAnswer != null) {// 判断该id的问题是否存在
					if (tempAnswer.getUserId().equals(userId)) {// 判断是否所属用户的回答
						// 修改保存
						answer.setModifyTime(now);
						flag = answerMapper.updateByPrimaryKeySelective(answer);
					} else {
						log.error("没有修改该回答的权限");
						result.setResultEnum(ResultEnum.ANSWER_MODIFY_AUTH);
					}
				} else {
					log.error("没有找到该回答数据");
					result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
				}
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("回答成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("保存回答失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

	public Result deleteAnswer(String token, Integer id) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		Integer userId = TokenUtil.getUserId(token);
		try {
			Answer tempAnswer = answerMapper.selectByPrimaryKey(id);
			if (tempAnswer != null) {// 判断该id的问题是否存在
				if (tempAnswer.getUserId().equals(userId)) {// 判断是否所属用户的回答
					// 删除回答
					flag = answerMapper.deleteByPrimaryKey(id);
				} else {
					log.error("没有删除该回答的权限");
					result.setResultEnum(ResultEnum.ANSWER_MODIFY_AUTH);
				}
			} else {
				log.error("没有找到该回答数据");
				result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("回答删除:" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除回答失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

}
