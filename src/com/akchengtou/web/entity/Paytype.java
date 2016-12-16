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
 * Paytype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "paytype", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"servicetypes"})
public class Paytype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private Set<Servicetype> servicetypes = new HashSet<Servicetype>(0);

	// Constructors

	/** default constructor */
	public Paytype() {
	}

	/** full constructor */
	public Paytype(String name, Set<Servicetype> servicetypes) {
		this.name = name;
		this.servicetypes = servicetypes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paytype")
	public Set<Servicetype> getServicetypes() {
		return this.servicetypes;
	}

	public void setServicetypes(Set<Servicetype> servicetypes) {
		this.servicetypes = servicetypes;
	}

}