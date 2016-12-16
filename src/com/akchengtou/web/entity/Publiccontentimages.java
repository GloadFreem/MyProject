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
 * Publiccontentimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publiccontentimages", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"publiccontent"})
public class Publiccontentimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Publiccontent publiccontent;
	private String url;

	// Constructors

	/** default constructor */
	public Publiccontentimages() {
	}

	/** full constructor */
	public Publiccontentimages(Publiccontent publiccontent, String url) {
		this.publiccontent = publiccontent;
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
	@JoinColumn(name = "content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}