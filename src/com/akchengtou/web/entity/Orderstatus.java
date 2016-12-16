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
 * Orderstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderstatus", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"orderservices"})
public class Orderstatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private String name;
	private Set<Orderservice> orderservices = new HashSet<Orderservice>(0);

	// Constructors

	/** default constructor */
	public Orderstatus() {
	}

	/** full constructor */
	public Orderstatus(String name, Set<Orderservice> orderservices) {
		this.name = name;
		this.orderservices = orderservices;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderstatus")
	public Set<Orderservice> getOrderservices() {
		return this.orderservices;
	}

	public void setOrderservices(Set<Orderservice> orderservices) {
		this.orderservices = orderservices;
	}

}