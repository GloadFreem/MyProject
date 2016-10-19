package com.akchengtou.web.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "event", catalog = "ak_zhsq")
public class Event implements java.io.Serializable {

	// Fields

	private Integer eventId;
	private User user;
	private String content;
	private Set<Eventimages> eventimageses = new HashSet<Eventimages>(0);

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** minimal constructor */
	public Event(Integer eventId) {
		this.eventId = eventId;
	}

	/** full constructor */
	public Event(Integer eventId, User user, String content,
			Set<Eventimages> eventimageses) {
		this.eventId = eventId;
		this.user = user;
		this.content = content;
		this.eventimageses = eventimageses;
	}

	// Property accessors
	@Id
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
	public Set<Eventimages> getEventimageses() {
		return this.eventimageses;
	}

	public void setEventimageses(Set<Eventimages> eventimageses) {
		this.eventimageses = eventimageses;
	}

}