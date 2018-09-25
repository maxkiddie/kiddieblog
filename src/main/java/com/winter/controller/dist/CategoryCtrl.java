package com.winter.controller.dist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winter.base.Result;
import com.winter.model.Category;
import com.winter.service.category.CategoryService;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/distApi/category")
public class CategoryCtrl {

	@Autowired
	private CategoryService categoryService;

	@ResponseBody
	@RequestMapping(value = "/save", produces = { "application/json;charset=UTF-8" })
	public Result save(Category category, String token) {
		return categoryService.saveOrUpdateCategory(token, category);
	}

	@ResponseBody
	@RequestMapping(value = "/delete", produces = { "application/json;charset=UTF-8" })
	public Result login(Integer id, String token) {
		return categoryService.deleteCategory(token, id);
	}

}
