package com.akchengtou.web.entity;

import java.sql.Timestamp;
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
 * Authentic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authentic", catalog = "ak_zhsq")
public class Authentic implements java.io.Serializable {

	// Fields

	private Integer authId;
	private User user;
	private Authenticstatus authenticstatus;
	private Timestamp authDate;
	private Short status;
	private Set<Homehouse> homehouses = new HashSet<Homehouse>(0);

	// Constructors

	/** default constructor */
	public Authentic() {
	}

	/** minimal constructor */
	public Authentic(Integer authId) {
		this.authId = authId;
	}

	/** full constructor */
	public Authentic(Integer authId, User user,
			Authenticstatus authenticstatus, Timestamp authDate, Short status,
			Set<Homehouse> homehouses) {
		this.authId = authId;
		this.user = user;
		this.authenticstatus = authenticstatus;
		this.authDate = authDate;
		this.status = status;
		this.homehouses = homehouses;
	}

	// Property accessors
	@Id
	@Column(name = "auth_id", unique = true, nullable = false)
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public Authenticstatus getAuthenticstatus() {
		return this.authenticstatus;
	}

	public void setAuthenticstatus(Authenticstatus authenticstatus) {
		this.authenticstatus = authenticstatus;
	}

	@Column(name = "auth_date", length = 19)
	public Timestamp getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Timestamp authDate) {
		this.authDate = authDate;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authentic")
	public Set<Homehouse> getHomehouses() {
		return this.homehouses;
	}

	public void setHomehouses(Set<Homehouse> homehouses) {
		this.homehouses = homehouses;
	}

}