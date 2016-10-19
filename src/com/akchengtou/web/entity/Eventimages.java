package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Eventimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "eventimages", catalog = "ak_zhsq")
public class Eventimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Event event;
	private Integer url;

	// Constructors

	/** default constructor */
	public Eventimages() {
	}

	/** minimal constructor */
	public Eventimages(Integer imageId) {
		this.imageId = imageId;
	}

	/** full constructor */
	public Eventimages(Integer imageId, Event event, Integer url) {
		this.imageId = imageId;
		this.event = event;
		this.url = url;
	}

	// Property accessors
	@Id
	@Column(name = "image_id", unique = true, nullable = false)
	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Column(name = "url")
	public Integer getUrl() {
		return this.url;
	}

	public void setUrl(Integer url) {
		this.url = url;
	}

}