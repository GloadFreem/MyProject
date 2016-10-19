package com.akchengtou.web.entity;

import java.sql.Timestamp;
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
 * Attendance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendance", catalog = "ak_zhsq")
public class Attendance implements java.io.Serializable {

	// Fields

	private Integer attendId;
	private User user;
	private Unit unit;
	private Timestamp requireDate;
	private String content;
	private Set<Attendancerecord> attendancerecords = new HashSet<Attendancerecord>(
			0);

	// Constructors

	/** default constructor */
	public Attendance() {
	}

	/** minimal constructor */
	public Attendance(Integer attendId) {
		this.attendId = attendId;
	}

	/** full constructor */
	public Attendance(Integer attendId, User user, Unit unit,
			Timestamp requireDate, String content,
			Set<Attendancerecord> attendancerecords) {
		this.attendId = attendId;
		this.user = user;
		this.unit = unit;
		this.requireDate = requireDate;
		this.content = content;
		this.attendancerecords = attendancerecords;
	}

	// Property accessors
	@Id
	@Column(name = "attend_id", unique = true, nullable = false)
	public Integer getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Integer attendId) {
		this.attendId = attendId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Column(name = "require_date", length = 19)
	public Timestamp getRequireDate() {
		return this.requireDate;
	}

	public void setRequireDate(Timestamp requireDate) {
		this.requireDate = requireDate;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attendance")
	public Set<Attendancerecord> getAttendancerecords() {
		return this.attendancerecords;
	}

	public void setAttendancerecords(Set<Attendancerecord> attendancerecords) {
		this.attendancerecords = attendancerecords;
	}

}