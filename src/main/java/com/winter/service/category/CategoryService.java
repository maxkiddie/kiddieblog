package com.winter.service.category;

import com.winter.base.Result;
import com.winter.model.Category;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:50:56
 */
public interface CategoryService {

	Result saveOrUpdateCategory(String token, Category category);

	Result deleteCategory(String token, Integer id);
}
