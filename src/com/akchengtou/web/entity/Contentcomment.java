package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contentcomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contentcomment", catalog = "ak_zhsq")
public class Contentcomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private User userByUserId;
	private User userByAtUserId;
	private Publiccontent publiccontent;
	private Boolean valid;

	// Constructors

	/** default constructor */
	public Contentcomment() {
	}

	/** minimal constructor */
	public Contentcomment(Integer commentId) {
		this.commentId = commentId;
	}

	/** full constructor */
	public Contentcomment(Integer commentId, User userByUserId,
			User userByAtUserId, Publiccontent publiccontent, Boolean valid) {
		this.commentId = commentId;
		this.userByUserId = userByUserId;
		this.userByAtUserId = userByAtUserId;
		this.publiccontent = publiccontent;
		this.valid = valid;
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
	@JoinColumn(name = "user_id")
	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "at_user_id")
	public User getUserByAtUserId() {
		return this.userByAtUserId;
	}

	public void setUserByAtUserId(User userByAtUserId) {
		this.userByAtUserId = userByAtUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

	@Column(name = "valid")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}