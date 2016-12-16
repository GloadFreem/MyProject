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
 * Eventimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "eventimages", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"event"})
public class Eventimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Event event;
	private String url;

	// Constructors

	/** default constructor */
	public Eventimages() {
	}

	/** full constructor */
	public Eventimages(Event event, String url) {
		this.event = event;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}