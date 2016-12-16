package com.akchengtou.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Ordercomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordercomment", catalog = "ak_zhsq")
public class Ordercomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Orderservice orderservice;
	private Float score;
	private Date commentDate;

	// Constructors

	/** default constructor */
	public Ordercomment() {
	}

	/** full constructor */
	public Ordercomment(Orderservice orderservice, Float score,
			Date commentDate) {
		this.orderservice = orderservice;
		this.score = score;
		this.commentDate = commentDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orderservice getOrderservice() {
		return this.orderservice;
	}

	public void setOrderservice(Orderservice orderservice) {
		this.orderservice = orderservice;
	}

	@Column(name = "score", precision = 12, scale = 0)
	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Column(name = "comment_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}