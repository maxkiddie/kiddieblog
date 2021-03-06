/**
 * 
 */
package com.winter.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午9:27:23
 */
@Table(name = "category")
public class Category {
	@Id
	private Integer id;
	@NotBlank(message = "分类名称不能为空")
	@Length(min = 2, max = 20, message = "分类名称长度应处于[2,20]个字符")
	private String name;
	@NotNull(message = "是否显示不能为空")
	private Integer isShow;
	@NotNull(message = "排序序号不能为空")
	@Range(min = 0, max = 100, message = "排序只能处于[0,100]")
	private Integer rank;
	private Date modifyTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isShow
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime
	 *            the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
