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
 * Contact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contact", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"homehouse"})
public class Contact implements java.io.Serializable {

	// Fields

	private Integer contactId;
	private Homehouse homehouse;
	private String name;
	private String telephone;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** minimal constructor */
	public Contact(Homehouse homehouse) {
		this.homehouse = homehouse;
	}

	/** full constructor */
	public Contact(Homehouse homehouse, String name,String telephone) {
		this.homehouse = homehouse;
		this.name = name;
		this.telephone = telephone;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "contact_id", unique = true, nullable = false)
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_id", nullable = false)
	public Homehouse getHomehouse() {
		return this.homehouse;
	}

	public void setHomehouse(Homehouse homehouse) {
		this.homehouse = homehouse;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}