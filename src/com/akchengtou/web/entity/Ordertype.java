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
 * Ordertype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordertype", catalog = "ak_zhsq")
public class Ordertype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private String image;
	private Set<Orderservice> orderservices = new HashSet<Orderservice>(0);

	// Constructors

	/** default constructor */
	public Ordertype() {
	}

	/** minimal constructor */
	public Ordertype(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public Ordertype(Integer typeId, String name, String image,
			Set<Orderservice> orderservices) {
		this.typeId = typeId;
		this.name = name;
		this.image = image;
		this.orderservices = orderservices;
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

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordertype")
	public Set<Orderservice> getOrderservices() {
		return this.orderservices;
	}

	public void setOrderservices(Set<Orderservice> orderservices) {
		this.orderservices = orderservices;
	}

}