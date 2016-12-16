package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contentcomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contentcomment", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"publiccontent"})
public class Contentcomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private String content;
	private User userByUserId;
	private User userByAtUserId;
	private Publiccontent publiccontent;
	private Boolean valid;

	// Constructors

	/** default constructor */
	public Contentcomment() {
	}

	/** full constructor */
	public Contentcomment(User userByUserId, User userByAtUserId,
			Publiccontent publiccontent, Boolean valid) {
		this.userByUserId = userByUserId;
		this.userByAtUserId = userByAtUserId;
		this.publiccontent = publiccontent;
		this.valid = valid;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}