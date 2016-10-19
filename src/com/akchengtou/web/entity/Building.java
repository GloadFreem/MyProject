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

/**
 * Building entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "building", catalog = "ak_zhsq")
public class Building implements java.io.Serializable {

	// Fields

	private Integer buildId;
	private Homehouse homehouse;
	private String name;
	private Boolean valid;
	private Set<Unit> units = new HashSet<Unit>(0);

	// Constructors

	/** default constructor */
	public Building() {
	}

	/** minimal constructor */
	public Building(Integer buildId) {
		this.buildId = buildId;
	}

	/** full constructor */
	public Building(Integer buildId, Homehouse homehouse, String name,
			Boolean valid, Set<Unit> units) {
		this.buildId = buildId;
		this.homehouse = homehouse;
		this.name = name;
		this.valid = valid;
		this.units = units;
	}

	// Property accessors
	@Id
	@Column(name = "build_id", unique = true, nullable = false)
	public Integer getBuildId() {
		return this.buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_id")
	public Homehouse getHomehouse() {
		return this.homehouse;
	}

	public void setHomehouse(Homehouse homehouse) {
		this.homehouse = homehouse;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "building")
	public Set<Unit> getUnits() {
		return this.units;
	}

	public void setUnits(Set<Unit> units) {
		this.units = units;
	}

}