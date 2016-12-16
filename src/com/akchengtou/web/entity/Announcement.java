package com.akchengtou.web.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Announcement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "announcement", catalog = "ak_zhsq")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Announcement implements java.io.Serializable {

	// Fields

	private Integer announceId;
	private String content;
	private String title;
	private Date publicDate;
	private Boolean readed;

	// Constructors

	/** default constructor */
	public Announcement() {
	}

	/** minimal constructor */
	public Announcement(Integer announceId) {
		this.announceId = announceId;
	}

	/** full constructor */
	public Announcement(Integer announceId, String content,
			Date publicDate, Boolean readed) {
		this.announceId = announceId;
		this.content = content;
		this.publicDate = publicDate;
		this.readed = readed;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "announce_id", unique = true, nullable = false)
	public Integer getAnnounceId() {
		return this.announceId;
	}

	public void setAnnounceId(Integer announceId) {
		this.announceId = announceId;
	}

	@Column(name = "content", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "public_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")  
	public Date getPublicDate() {
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	@Column(name = "readed")
	public Boolean getReaded() {
		return this.readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	/**
	 * @return the title
	 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}