package com.akchengtou.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Attendancerecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendancerecord", catalog = "ak_zhsq")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"user"})
//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","user"})
public class Attendancerecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private User user;
	private Attendance attendance;
	private Date attendDate;

	// Constructors

	/** default constructor */
	public Attendancerecord() {
	}

	/** full constructor */
	public Attendancerecord(User user, Attendance attendance,
			Date attendDate) {
		this.user = user;
		this.attendance = attendance;
		this.attendDate = attendDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "record_id", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attend_id")
	public Attendance getAttendance() {
		return this.attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	@Column(name = "attend_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getAttendDate() {
		return this.attendDate;
	}

	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}

}