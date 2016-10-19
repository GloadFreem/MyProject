package com.akchengtou.web.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ordercomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordercomment", catalog = "ak_zhsq")
public class Ordercomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private User user;
	private Orderservice orderservice;
	private String content;
	private Float score;
	private Double gender;
	private Timestamp commentDate;

	// Constructors

	/** default constructor */
	public Ordercomment() {
	}

	/** minimal constructor */
	public Ordercomment(Integer commentId) {
		this.commentId = commentId;
	}

	/** full constructor */
	public Ordercomment(Integer commentId, User user,
			Orderservice orderservice, String content, Float score,
			Double gender, Timestamp commentDate) {
		this.commentId = commentId;
		this.user = user;
		this.orderservice = orderservice;
		this.content = content;
		this.score = score;
		this.gender = gender;
		this.commentDate = commentDate;
	}

	// Property accessors
	@Id
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "at_user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orderservice getOrderservice() {
		return this.orderservice;
	}

	public void setOrderservice(Orderservice orderservice) {
		this.orderservice = orderservice;
	}

	@Column(name = "content", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "score", precision = 12, scale = 0)
	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Column(name = "gender", precision = 22, scale = 0)
	public Double getGender() {
		return this.gender;
	}

	public void setGender(Double gender) {
		this.gender = gender;
	}

	@Column(name = "comment_date", length = 19)
	public Timestamp getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

}