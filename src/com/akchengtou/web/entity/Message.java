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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"user"})
public class Message implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private User user;
	private String content;
	private String title;
	private Boolean readed;
	private Boolean valid;
	private Date publicDate;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(User user, String content, String title, Boolean readed) {
		this.user = user;
		this.content = content;
		this.title = title;
		this.readed = readed;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@Column(name = "valid")
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the publicDate
	 */
	@Column(name="public_date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	public Date getPublicDate() {
		return publicDate;
	}

	/**
	 * @param publicDate the publicDate to set
	 */
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

}