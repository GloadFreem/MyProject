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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Charges entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "charges", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"charges"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Charges implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private String name;
	private Double price;
	private Integer chargeId;
	private Set<Charge> charges = new HashSet<Charge>(0);

	// Constructors

	/** default constructor */
	public Charges() {
	}

	/** full constructor */
	public Charges(String name, Double price, Integer chargeId,
			Set<Charge> charges) {
		this.name = name;
		this.price = price;
		this.chargeId = chargeId;
		this.charges = charges;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "charge_id")
	public Integer getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "charges")
	public Set<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}

}