package com.akchengtou.web.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Paytype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "paytype", catalog = "ak_zhsq")
public class Paytype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private Set<Service> services = new HashSet<Service>(0);

	// Constructors

	/** default constructor */
	public Paytype() {
	}

	/** minimal constructor */
	public Paytype(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public Paytype(Integer typeId, String name, Set<Service> services) {
		this.typeId = typeId;
		this.name = name;
		this.services = services;
	}

	// Property accessors
	@Id
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
	public Set<Service> getServices() {
		return this.services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

}