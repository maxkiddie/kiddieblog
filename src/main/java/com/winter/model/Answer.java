/**
 * 
 */
package com.winter.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午9:17:11
 */
@Table(name = "answer")
public class Answer {
	@Id
	private Integer id;
	private Integer userId;
	private String content;
	private Date answerTime;
	private Integer goodCount;
	private Integer badCount;

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

}
