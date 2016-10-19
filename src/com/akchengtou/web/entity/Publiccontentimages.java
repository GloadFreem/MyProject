package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Publiccontentimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publiccontentimages", catalog = "ak_zhsq")
public class Publiccontentimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Publiccontent publiccontent;
	private String url;

	// Constructors

	/** default constructor */
	public Publiccontentimages() {
	}

	/** minimal constructor */
	public Publiccontentimages(Integer imageId) {
		this.imageId = imageId;
	}

	/** full constructor */
	public Publiccontentimages(Integer imageId, Publiccontent publiccontent,
			String url) {
		this.imageId = imageId;
		this.publiccontent = publiccontent;
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