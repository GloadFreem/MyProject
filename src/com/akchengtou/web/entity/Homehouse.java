package com.akchengtou.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Homehouse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homehouse", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"buildings"})
public class Homehouse implements java.io.Serializable {

	// Fields

	private Integer homeId;
	private String name;
	private Boolean valid;
	private Set<Building> buildings = new HashSet<Building>(0);
	private Set<Contact> contacts = new HashSet<Contact>(0);

	// Constructors

	/** default constructor */
	public Homehouse() {
	}

	/** full constructor */
	public Homehouse(String name, Boolean valid, Set<Building> buildings,
			Set<Contact> contacts) {
		this.name = name;
		this.valid = valid;
		this.buildings = buildings;
		this.contacts = contacts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "home_id", unique = true, nullable = false)
	public Integer getHomeId() {
		return this.homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "homehouse")
	public Set<Building> getBuildings() {
		return this.buildings;
	}

	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "homehouse")
	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

}