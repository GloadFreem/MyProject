package com.akchengtou.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Authentic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authentic", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"user"})
public class Authentic implements java.io.Serializable {

	// Fields

	private Integer authId;
	private User user;
	private String idCard;
	private String name;
	private House house;
	private Identity identity;
	private Authenticstatus authenticstatus;
	private Date authDate;

	// Constructors

	/** default constructor */
	public Authentic() {
	}

	/** full constructor */
	public Authentic(User user, House house, Identity identity,
			Authenticstatus authenticstatus, Date authDate, Short status,String name) {
		this.user = user;
		this.house = house;
		this.identity = identity;
		this.authenticstatus = authenticstatus;
		this.authDate = authDate;
		this.name = name;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hourse_id")
	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "identiy_type_id")
	public Identity getIdentity() {
		return this.identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	public Authenticstatus getAuthenticstatus() {
		return this.authenticstatus;
	}

	public void setAuthenticstatus(Authenticstatus authenticstatus) {
		this.authenticstatus = authenticstatus;
	}

	@Column(name = "auth_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idCard
	 */
	@Column(name="idCard")
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}