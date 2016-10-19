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
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task", catalog = "ak_zhsq")
public class Task implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private User user;
	private Service service;
	private Timestamp taskDate;

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** minimal constructor */
	public Task(Integer taskId) {
		this.taskId = taskId;
	}

	/** full constructor */
	public Task(Integer taskId, User user, Service service, Timestamp taskDate) {
		this.taskId = taskId;
		this.user = user;
		this.service = service;
		this.taskDate = taskDate;
	}

	// Property accessors
	@Id
	@Column(name = "task_id", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_ower")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Column(name = "task_date", length = 19)
	public Timestamp getTaskDate() {
		return this.taskDate;
	}

	public void setTaskDate(Timestamp taskDate) {
		this.taskDate = taskDate;
	}

}