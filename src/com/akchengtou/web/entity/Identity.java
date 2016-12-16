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
 * Identity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "identity", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"authentics"})
public class Identity implements java.io.Serializable {

	// Fields

	private Integer identiyTypeId;
	private String name;
	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public Identity() {
	}

	/** full constructor */
	public Identity(String name, Set<Authentic> authentics) {
		this.name = name;
		this.authentics = authentics;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "identiy_type_id", unique = true, nullable = false)
	public Integer getIdentiyTypeId() {
		return this.identiyTypeId;
	}

	public void setIdentiyTypeId(Integer identiyTypeId) {
		this.identiyTypeId = identiyTypeId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "identity")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

}