package com.akchengtou.web.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Announcement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "announcement", catalog = "ak_zhsq")
public class Announcement implements java.io.Serializable {

	// Fields

	private Integer announceId;
	private String content;
	private Timestamp publicDate;
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
			Timestamp publicDate, Boolean readed) {
		this.announceId = announceId;
		this.content = content;
		this.publicDate = publicDate;
		this.readed = readed;
	}

	// Property accessors
	@Id
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
	public Timestamp getPublicDate() {
		return this.publicDate;
	}

	public void setPublicDate(Timestamp publicDate) {
		this.publicDate = publicDate;
	}

	@Column(name = "readed")
	public Boolean getReaded() {
		return this.readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

}