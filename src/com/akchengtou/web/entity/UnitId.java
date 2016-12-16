package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UnitId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UnitId implements java.io.Serializable {

	// Fields

	private Integer buildId;
	private Integer unitId;

	// Constructors

	/** default constructor */
	public UnitId() {
	}

	/** full constructor */
	public UnitId(Integer buildId, Integer unitId) {
		this.buildId = buildId;
		this.unitId = unitId;
	}

	// Property accessors

	@Column(name = "build_id", nullable = false)
	public Integer getBuildId() {
		return this.buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	@Column(name = "unit_id", nullable = false)
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UnitId))
			return false;
		UnitId castOther = (UnitId) other;

		return ((this.getBuildId() == castOther.getBuildId()) || (this
				.getBuildId() != null && castOther.getBuildId() != null && this
				.getBuildId().equals(castOther.getBuildId())))
				&& ((this.getUnitId() == castOther.getUnitId()) || (this
						.getUnitId() != null && castOther.getUnitId() != null && this
						.getUnitId().equals(castOther.getUnitId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBuildId() == null ? 0 : this.getBuildId().hashCode());
		result = 37 * result
				+ (getUnitId() == null ? 0 : this.getUnitId().hashCode());
		return result;
	}

}