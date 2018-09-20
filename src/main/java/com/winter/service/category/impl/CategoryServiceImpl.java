package com.winter.service.category.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.mapper.CategoryMapper;
import com.winter.model.Category;
import com.winter.service.category.CategoryService;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private final static Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryMapper categoryMapper;

	public Result saveOrUpdateCategory(String token, Category category) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		try {
			if (StringUtils.isEmpty(category.getId())) {
				category.setModifyTime(new Date());
				flag = categoryMapper.insertSelective(category);
			} else {// 保存修改
				Category tempCategory = categoryMapper.selectByPrimaryKey(category.getId());
				if (tempCategory != null) {// 判断该id的分类是否存在
					// 修改保存
					category.setModifyTime(new Date());
					flag = categoryMapper.updateByPrimaryKeySelective(category);
				} else {
					log.error("没有找到该分类数据");
					result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
				}
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("保存分类成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("保存分类失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

	public Result deleteCategory(String token, Integer id) {
		Result result = new Result();
		int flag = 0;
		// id不存在存在则添加
		try {
			Category tempCategory = categoryMapper.selectByPrimaryKey(id);
			if (tempCategory != null) {// 判断该id的分类是否存在
				flag = categoryMapper.deleteByPrimaryKey(id);
			} else {
				log.error("没有找到该分类数据");
				result.setResultEnum(ResultEnum.DATA_NOT_FOUND);
			}
			if (flag != 0) {
				result.setResultEnum(ResultEnum.SUSS);
				log.info("分类删除:" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除分类失败");
			result.setResultEnum(ResultEnum.DB_FAIL);
		}
		return result;
	}

}
