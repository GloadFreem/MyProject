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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Service entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"tasks","orderservices","user"})
public class Service implements java.io.Serializable {

	// Fields

	private Integer serviceId;
	private Servicetype servicetype;
	private Member member;
	private User user;
	private String name;
	private Date serviceDate;
	private String content;
	private Set<Serviceimages> serviceimageses = new HashSet<Serviceimages>(0);
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Orderservice> orderservices = new HashSet<Orderservice>(0);

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** full constructor */
	public Service(Servicetype servicetype, Member member,
			String name, Date serviceDate, String content,
			Set<Serviceimages> serviceimageses, Set<Task> tasks,
			Set<Orderservice> orderservices) {
		this.servicetype = servicetype;
		this.member = member;
		this.name = name;
		this.serviceDate = serviceDate;
		this.content = content;
		this.serviceimageses = serviceimageses;
		this.tasks = tasks;
		this.orderservices = orderservices;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "service_id", unique = true, nullable = false)
	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}


	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "service_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "service")
	public Set<Serviceimages> getServiceimageses() {
		return this.serviceimageses;
	}

	public void setServiceimageses(Set<Serviceimages> serviceimageses) {
		this.serviceimageses = serviceimageses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	public Set<Orderservice> getOrderservices() {
		return this.orderservices;
	}

	public void setOrderservices(Set<Orderservice> orderservices) {
		this.orderservices = orderservices;
	}

	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}