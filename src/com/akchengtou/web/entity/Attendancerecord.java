package com.akchengtou.web.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Attendancerecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendancerecord", catalog = "ak_zhsq")
public class Attendancerecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private User user;
	private Attendance attendance;
	private Timestamp attendDate;

	// Constructors

	/** default constructor */
	public Attendancerecord() {
	}

	/** minimal constructor */
	public Attendancerecord(Integer recordId) {
		this.recordId = recordId;
	}

	/** full constructor */
	public Attendancerecord(Integer recordId, User user, Attendance attendance,
			Timestamp attendDate) {
		this.recordId = recordId;
		this.user = user;
		this.attendance = attendance;
		this.attendDate = attendDate;
	}

	// Property accessors
	@Id
	@Column(name = "record_id", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
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
	@JoinColumn(name = "attend_id")
	public Attendance getAttendance() {
		return this.attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	@Column(name = "attend_date", length = 19)
	public Timestamp getAttendDate() {
		return this.attendDate;
	}

	public void setAttendDate(Timestamp attendDate) {
		this.attendDate = attendDate;
	}

}