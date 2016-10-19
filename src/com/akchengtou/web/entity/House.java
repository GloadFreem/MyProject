package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * House entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house", catalog = "ak_zhsq")
public class House implements java.io.Serializable {

	// Fields

	private Integer hourseId;
	private Unit unit;
	private String name;

	// Constructors

	/** default constructor */
	public House() {
	}

	/** minimal constructor */
	public House(Integer hourseId) {
		this.hourseId = hourseId;
	}

	/** full constructor */
	public House(Integer hourseId, Unit unit, String name) {
		this.hourseId = hourseId;
		this.unit = unit;
		this.name = name;
	}

	// Property accessors
	@Id
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

}