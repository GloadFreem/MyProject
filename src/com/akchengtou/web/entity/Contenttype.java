package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contenttype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contenttype", catalog = "ak_zhsq")
public class Contenttype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private Publiccontent publiccontent;
	private String name;
	private Boolean valid;

	// Constructors

	/** default constructor */
	public Contenttype() {
	}

	/** minimal constructor */
	public Contenttype(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public Contenttype(Integer typeId, Publiccontent publiccontent,
			String name, Boolean valid) {
		this.typeId = typeId;
		this.publiccontent = publiccontent;
		this.name = name;
		this.valid = valid;
	}

	// Property accessors
	@Id
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "valid")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}