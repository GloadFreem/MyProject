package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "ak_zhsq")
public class Message implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private User user;
	private String content;
	private String title;
	private Boolean readed;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(Integer messageId) {
		this.messageId = messageId;
	}

	/** full constructor */
	public Message(Integer messageId, User user, String content, String title,
			Boolean readed) {
		this.messageId = messageId;
		this.user = user;
		this.content = content;
		this.title = title;
		this.readed = readed;
	}

	// Property accessors
	@Id
	@Column(name = "message_id", unique = true, nullable = false)
	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "content", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "readed")
	public Boolean getReaded() {
		return this.readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

}