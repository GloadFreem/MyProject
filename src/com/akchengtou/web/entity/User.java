package com.akchengtou.web.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "ak_zhsq")
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String name;
	private Long telephone;
	private String image;
	private String introduce;
	private Timestamp registDate;
	private Timestamp loginTime;
	private Integer score;
	private Short gender;
	private Set<Contentcomment> contentcommentsForUserId = new HashSet<Contentcomment>(
			0);
	private Set<Attendance> attendances = new HashSet<Attendance>(0);
	private Set<Event> events = new HashSet<Event>(0);
	private Set<Contentcomment> contentcommentsForAtUserId = new HashSet<Contentcomment>(
			0);
	private Set<Member> members = new HashSet<Member>(0);
	private Set<Attendancerecord> attendancerecords = new HashSet<Attendancerecord>(
			0);
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Feedback> feedbacks = new HashSet<Feedback>(0);
	private Set<Ordercomment> ordercomments = new HashSet<Ordercomment>(0);
	private Set<Publiccontent> publiccontents = new HashSet<Publiccontent>(0);
	private Set<Message> messages = new HashSet<Message>(0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);
	private Set<Authentic> authentics = new HashSet<Authentic>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public User(Integer userId, String name, Long telephone, String image,
			String introduce, Timestamp registDate, Timestamp loginTime,
			Integer score, Short gender,
			Set<Contentcomment> contentcommentsForUserId,
			Set<Attendance> attendances, Set<Event> events,
			Set<Contentcomment> contentcommentsForAtUserId,
			Set<Member> members, Set<Attendancerecord> attendancerecords,
			Set<Task> tasks, Set<Feedback> feedbacks,
			Set<Ordercomment> ordercomments, Set<Publiccontent> publiccontents,
			Set<Message> messages, Set<Contentprise> contentprises,
			Set<Authentic> authentics) {
		this.userId = userId;
		this.name = name;
		this.telephone = telephone;
		this.image = image;
		this.introduce = introduce;
		this.registDate = registDate;
		this.loginTime = loginTime;
		this.score = score;
		this.gender = gender;
		this.contentcommentsForUserId = contentcommentsForUserId;
		this.attendances = attendances;
		this.events = events;
		this.contentcommentsForAtUserId = contentcommentsForAtUserId;
		this.members = members;
		this.attendancerecords = attendancerecords;
		this.tasks = tasks;
		this.feedbacks = feedbacks;
		this.ordercomments = ordercomments;
		this.publiccontents = publiccontents;
		this.messages = messages;
		this.contentprises = contentprises;
		this.authentics = authentics;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", precision = 11, scale = 0)
	public Long getTelephone() {
		return this.telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "introduce", length = 1000)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "regist_date", length = 19)
	public Timestamp getRegistDate() {
		return this.registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}

	@Column(name = "login_time", length = 19)
	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "gender")
	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByUserId")
	public Set<Contentcomment> getContentcommentsForUserId() {
		return this.contentcommentsForUserId;
	}

	public void setContentcommentsForUserId(
			Set<Contentcomment> contentcommentsForUserId) {
		this.contentcommentsForUserId = contentcommentsForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByAtUserId")
	public Set<Contentcomment> getContentcommentsForAtUserId() {
		return this.contentcommentsForAtUserId;
	}

	public void setContentcommentsForAtUserId(
			Set<Contentcomment> contentcommentsForAtUserId) {
		this.contentcommentsForAtUserId = contentcommentsForAtUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Attendancerecord> getAttendancerecords() {
		return this.attendancerecords;
	}

	public void setAttendancerecords(Set<Attendancerecord> attendancerecords) {
		this.attendancerecords = attendancerecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Ordercomment> getOrdercomments() {
		return this.ordercomments;
	}

	public void setOrdercomments(Set<Ordercomment> ordercomments) {
		this.ordercomments = ordercomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Publiccontent> getPubliccontents() {
		return this.publiccontents;
	}

	public void setPubliccontents(Set<Publiccontent> publiccontents) {
		this.publiccontents = publiccontents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Contentprise> getContentprises() {
		return this.contentprises;
	}

	public void setContentprises(Set<Contentprise> contentprises) {
		this.contentprises = contentprises;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

}