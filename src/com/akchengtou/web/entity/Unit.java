package com.akchengtou.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Unit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "unit", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"building","houses"})
public class Unit implements java.io.Serializable {

	// Fields

	private Integer unitId;
	private Building building;
	private String name;
	private Set<House> houses = new HashSet<House>(0);

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(Integer unitId, Building building) {
		this.unitId = unitId;
		this.building = building;
	}

	/** full constructor */
	public Unit(Integer unitId, Building building, String name,
			Set<House> houses) {
		this.unitId = unitId;
		this.building = building;
		this.name = name;
		this.houses = houses;
	}

	// Property accessors
	@Id
	@Column(name = "unit_id", unique = true, nullable = false)
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "build_id", nullable = false)
	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unit")
	public Set<House> getHouses() {
		return this.houses;
	}

	public void setHouses(Set<House> houses) {
		this.houses = houses;
	}

}