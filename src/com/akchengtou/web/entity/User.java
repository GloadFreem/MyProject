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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "ak_zhsq")
@JsonIgnoreProperties(value = { "contentcommentsForUserId", "attendances",
		"events", "contentcommentsForAtUserId", "members", "attendancerecords",
		"tasks", "feedbacks", "ordercomments", "publiccontents", "messages",
		"contentprises","orderServices" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String name;
	private String telephone;
	private String image;
	private String intro;
	private Date registDate;
	private Date loginTime;
	private Integer score;
	private Short gender;
	private String regId;
	private Short platform;
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
	private Set<Publiccontent> publiccontents = new HashSet<Publiccontent>(0);
	private Set<Message> messages = new HashSet<Message>(0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);
	private Set<Authentic> authentics = new HashSet<Authentic>(0);
	private Set<Service> orderServices = new HashSet<Service>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String telephone, String image, String introduce,
			Date registDate, Date loginTime, Integer score, Short gender,
			String regId, Short platform,
			Set<Contentcomment> contentcommentsForUserId,
			Set<Attendance> attendances, Set<Event> events,
			Set<Contentcomment> contentcommentsForAtUserId,
			Set<Member> members, Set<Attendancerecord> attendancerecords,
			Set<Task> tasks, Set<Feedback> feedbacks,
			Set<Publiccontent> publiccontents, Set<Message> messages,
			Set<Contentprise> contentprises, Set<Authentic> authentics,
			Set<Service> orderServices) {
		this.name = name;
		this.telephone = telephone;
		this.image = image;
		this.intro = introduce;
		this.registDate = registDate;
		this.loginTime = loginTime;
		this.score = score;
		this.gender = gender;
		this.regId = regId;
		this.platform = platform;
		this.contentcommentsForUserId = contentcommentsForUserId;
		this.attendances = attendances;
		this.events = events;
		this.contentcommentsForAtUserId = contentcommentsForAtUserId;
		this.members = members;
		this.attendancerecords = attendancerecords;
		this.tasks = tasks;
		this.feedbacks = feedbacks;
		this.publiccontents = publiccontents;
		this.messages = messages;
		this.contentprises = contentprises;
		this.authentics = authentics;
		this.orderServices = orderServices;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@Column(name = "telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
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
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String introduce) {
		this.intro = introduce;
	}

	@Column(name = "regist_date", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getRegistDate() {
		return this.registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	@Column(name = "login_time", length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
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

	@Column(name = "reg_id", length = 100)
	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

	/**
	 * @return the orderServices
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Service> getOrderServices() {
		return orderServices;
	}

	/**
	 * @param orderServices
	 *            the orderServices to set
	 */
	public void setOrderServices(Set<Service> orderServices) {
		this.orderServices = orderServices;
	}

}