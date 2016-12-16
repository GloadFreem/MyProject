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

/**
 * Feedbackimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feedbackimages", catalog = "ak_zhsq")
public class Feedbackimages implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private Feedback feedback;
	private String url;

	// Constructors

	/** default constructor */
	public Feedbackimages() {
	}

	/** full constructor */
	public Feedbackimages(Feedback feedback, String url) {
		this.feedback = feedback;
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
	@JoinColumn(name = "feed_id")
	public Feedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Column(name = "url", length = 2000)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}