package com.akchengtou.web.entity;

import java.util.Date;
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
 * Attendance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendance", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"user","attendancerecords"})
public class Attendance implements java.io.Serializable {

	// Fields

	private Integer attendId;
	private User user;
	private Building building;
	private Date requireDate;
	private String content;
	private Set<Attendancerecord> attendancerecords = new HashSet<Attendancerecord>(
			0);

	// Constructors

	/** default constructor */
	public Attendance() {
	}

	/** full constructor */
	public Attendance(User user, Building building, Date requireDate,
			String content, Set<Attendancerecord> attendancerecords) {
		this.user = user;
		this.building = building;
		this.requireDate = requireDate;
		this.content = content;
		this.attendancerecords = attendancerecords;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "build_id")
	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@Column(name = "require_date", length = 19)
	public Date getRequireDate() {
		return this.requireDate;
	}

	public void setRequireDate(Date requireDate) {
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