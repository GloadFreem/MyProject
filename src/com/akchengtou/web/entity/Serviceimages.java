package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Serviceimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "serviceimages", catalog = "ak_zhsq")
public class Serviceimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Service service;
	private String url;

	// Constructors

	/** default constructor */
	public Serviceimages() {
	}

	/** minimal constructor */
	public Serviceimages(Integer imageId) {
		this.imageId = imageId;
	}

	/** full constructor */
	public Serviceimages(Integer imageId, Service service, String url) {
		this.imageId = imageId;
		this.service = service;
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
	@JoinColumn(name = "service_id")
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}