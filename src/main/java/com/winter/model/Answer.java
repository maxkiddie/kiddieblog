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
 *         2018年8月2日 上午9:17:11
 */
@Table(name = "answer")
public class Answer {
	@Id
	private Integer id;
	@NotNull(message = "回答所属用户不能为空")
	private Integer userId;
	@NotBlank(message = "回答内容不能为空")
	@Length(min = 3, max = 1000, message = "回答内容长度应该处于[3,1000]个字节")
	private String content;
	private Date answerTime;
	private Date modifyTime;
	private Integer goodCount;
	private Integer badCount;
	private Integer isTop;
	private Integer isBlack;

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
	 * @return the answerTime
	 */
	public Date getAnswerTime() {
		return answerTime;
	}

	/**
	 * @param answerTime
	 *            the answerTime to set
	 */
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
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
	 * @return the badCount
	 */
	public Integer getBadCount() {
		return badCount;
	}

	/**
	 * @param badCount
	 *            the badCount to set
	 */
	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
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
	 * @return the isBlack
	 */
	public Integer getIsBlack() {
		return isBlack;
	}

	/**
	 * @param isBlack
	 *            the isBlack to set
	 */
	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
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
