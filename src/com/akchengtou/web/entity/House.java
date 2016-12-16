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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * House entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"authentics","unit"})
public class House implements java.io.Serializable {

	// Fields

	private Integer hourseId;
	private Unit unit;
	private String name;
	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public House() {
	}

	/** full constructor */
	public House(Unit unit, String name, Set<Authentic> authentics) {
		this.unit = unit;
		this.name = name;
		this.authentics = authentics;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "hourse_id", unique = true, nullable = false)
	public Integer getHourseId() {
		return this.hourseId;
	}

	public void setHourseId(Integer hourseId) {
		this.hourseId = hourseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "house")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

}