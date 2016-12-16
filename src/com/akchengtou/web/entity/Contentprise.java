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
 * Contentprise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contentprise", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"publiccontent"})
public class Contentprise implements java.io.Serializable {

	// Fields

	private Integer priseId;
	private User user;
	private Publiccontent publiccontent;

	// Constructors

	/** default constructor */
	public Contentprise() {
	}

	/** full constructor */
	public Contentprise(User user, Publiccontent publiccontent) {
		this.user = user;
		this.publiccontent = publiccontent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prise_id", unique = true, nullable = false)
	public Integer getPriseId() {
		return this.priseId;
	}

	public void setPriseId(Integer priseId) {
		this.priseId = priseId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

}