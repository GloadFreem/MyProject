package com.akchengtou.web.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contenttype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contenttype", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"publiccontent"})
public class Contenttype implements java.io.Serializable {

	// Fields

	private ContenttypeId id;
	private Publiccontent publiccontent;
	private String name;
	private Boolean valid;

	// Constructors

	/** default constructor */
	public Contenttype() {
	}

	/** minimal constructor */
	public Contenttype(ContenttypeId id, Publiccontent publiccontent) {
		this.id = id;
		this.publiccontent = publiccontent;
	}

	/** full constructor */
	public Contenttype(ContenttypeId id, Publiccontent publiccontent,
			String name, Boolean valid) {
		this.id = id;
		this.publiccontent = publiccontent;
		this.name = name;
		this.valid = valid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "contentId", column = @Column(name = "content_id", nullable = false)),
			@AttributeOverride(name = "typeId", column = @Column(name = "type_id", nullable = false)) })
	public ContenttypeId getId() {
		return this.id;
	}

	public void setId(ContenttypeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id", nullable = false, insertable = false, updatable = false)
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