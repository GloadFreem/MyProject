package com.akchengtou.web.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Publiccontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publiccontent", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={""})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Publiccontent implements java.io.Serializable {

	// Fields

	private Integer contentId;
	private boolean flag;
	private User user;
	private String content;
	private Date publicDate;
	private Boolean valid;
	private Set<Contentcomment> contentcomments = new HashSet<Contentcomment>(0);
	private Set<Publiccontentimages> publiccontentimageses = new HashSet<Publiccontentimages>(
			0);
	private Set<Contenttype> contenttypes = new HashSet<Contenttype>(0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);

	// Constructors

	/** default constructor */
	public Publiccontent() {
	}

	/** full constructor */
	public Publiccontent(User user, String content, Date publicDate,
			Boolean valid, Set<Contentcomment> contentcomments,
			Set<Publiccontentimages> publiccontentimageses,
			Set<Contenttype> contenttypes, Set<Contentprise> contentprises) {
		this.user = user;
		this.content = content;
		this.publicDate = publicDate;
		this.valid = valid;
		this.contentcomments = contentcomments;
		this.publiccontentimageses = publiccontentimageses;
		this.contenttypes = contenttypes;
		this.contentprises = contentprises;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "content_id", unique = true, nullable = false)
	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "content", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "public_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getPublicDate() {
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	@Column(name = "valid")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	@OrderBy("commentId")
	public Set<Contentcomment> getContentcomments() {
		return this.contentcomments;
	}

	public void setContentcomments(Set<Contentcomment> contentcomments) {
		this.contentcomments = contentcomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	public Set<Publiccontentimages> getPubliccontentimageses() {
		return this.publiccontentimageses;
	}

	public void setPubliccontentimageses(
			Set<Publiccontentimages> publiccontentimageses) {
		this.publiccontentimageses = publiccontentimageses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	public Set<Contenttype> getContenttypes() {
		return this.contenttypes;
	}

	public void setContenttypes(Set<Contenttype> contenttypes) {
		this.contenttypes = contenttypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	public Set<Contentprise> getContentprises() {
		return this.contentprises;
	}

	public void setContentprises(Set<Contentprise> contentprises) {
		this.contentprises = contentprises;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}