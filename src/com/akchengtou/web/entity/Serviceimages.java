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
 * Serviceimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "serviceimages", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"service"})
public class Serviceimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Service service;
	private String url;

	// Constructors

	/** default constructor */
	public Serviceimages() {
	}

	/** full constructor */
	public Serviceimages(Service service, String url) {
		this.service = service;
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