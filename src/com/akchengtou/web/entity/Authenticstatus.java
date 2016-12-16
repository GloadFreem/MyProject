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
 * Authenticstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authenticstatus", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"authentics","valid"})
public class Authenticstatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private String name;
	private Boolean valid;
	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public Authenticstatus() {
	}

	/** full constructor */
	public Authenticstatus(String name, Boolean valid, Set<Authentic> authentics) {
		this.name = name;
		this.valid = valid;
		this.authentics = authentics;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "name", length = 20)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authenticstatus")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

}