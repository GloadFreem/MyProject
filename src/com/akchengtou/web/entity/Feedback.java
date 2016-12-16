package com.akchengtou.web.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feedback", catalog = "ak_zhsq")
public class Feedback implements java.io.Serializable {

	// Fields

	private Integer feedId;
	private User user;
	private String content;
	private Date feedDate;
	private Set<Feedbackimages> feedbackimageses = new HashSet<Feedbackimages>(
			0);

	// Constructors

	/** default constructor */
	public Feedback() {
	}

	/** full constructor */
	public Feedback(User user, String content,
			Set<Feedbackimages> feedbackimageses) {
		this.user = user;
		this.content = content;
		this.feedbackimageses = feedbackimageses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feed_id", unique = true, nullable = false)
	public Integer getFeedId() {
		return this.feedId;
	}

	public void setFeedId(Integer feedId) {
		this.feedId = feedId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feedback")
	public Set<Feedbackimages> getFeedbackimageses() {
		return this.feedbackimageses;
	}

	public void setFeedbackimageses(Set<Feedbackimages> feedbackimageses) {
		this.feedbackimageses = feedbackimageses;
	}

	public Date getFeedDate() {
		return feedDate;
	}

	@Column(name = "feed_date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public void setFeedDate(Date feedDate) {
		this.feedDate = feedDate;
	}

}