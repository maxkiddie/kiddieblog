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

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午8:54:02
 */
@Table(name = "question")
public class Question {
	@Id
	private Integer id;
	@NotNull(message = "问题所属用户不能为空")
	private Integer userId;
	@NotNull(message = "回答所属分类不能为空")
	private Integer categoryId;
	@NotBlank(message = "回答标题不能为空")
	@Length(min = 3, max = 50, message = "标题长度应该处于[3,50]个字节")
	private String title;
	private String label;
	@NotBlank(message = "回答内容不能为空")
	@Length(min = 3, max = 4000, message = "问题内容长度应该处于[3,4000]个字节")
	private String content;
	private Integer isShow;
	private Integer isTop;
	private Integer answerCount;
	private Integer goodCount;
	private Integer storeCount;
	private Date createTime;
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the isTop
	 */
	public Integer getIsTop() {
		return isTop;
	}

	/**
	 * @param isTop
	 *            the isTop to set
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	/**
	 * @return the answerCount
	 */
	public Integer getAnswerCount() {
		return answerCount;
	}

	/**
	 * @param answerCount
	 *            the answerCount to set
	 */
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	/**
	 * @return the goodCount
	 */
	public Integer getGoodCount() {
		return goodCount;
	}

	/**
	 * @param goodCount
	 *            the goodCount to set
	 */
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	/**
	 * @return the storeCount
	 */
	public Integer getStoreCount() {
		return storeCount;
	}

	/**
	 * @param storeCount
	 *            the storeCount to set
	 */
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
