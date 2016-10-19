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
 * Service entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service", catalog = "ak_zhsq")
public class Service implements java.io.Serializable {

	// Fields

	private Integer serviceId;
	private Servicetype servicetype;
	private Member member;
	private Paytype paytype;
	private String name;
	private Integer price;
	private Timestamp serviceDate;
	private String content;
	private Set<Serviceimages> serviceimageses = new HashSet<Serviceimages>(0);
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Orderservice> orderservices = new HashSet<Orderservice>(0);

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(Integer serviceId) {
		this.serviceId = serviceId;
	}

	/** full constructor */
	public Service(Integer serviceId, Servicetype servicetype, Member member,
			Paytype paytype, String name, Integer price, Timestamp serviceDate,
			String content, Set<Serviceimages> serviceimageses,
			Set<Task> tasks, Set<Orderservice> orderservices) {
		this.serviceId = serviceId;
		this.servicetype = servicetype;
		this.member = member;
		this.paytype = paytype;
		this.name = name;
		this.price = price;
		this.serviceDate = serviceDate;
		this.content = content;
		this.serviceimageses = serviceimageses;
		this.tasks = tasks;
		this.orderservices = orderservices;
	}

	// Property accessors
	@Id
	@Column(name = "service_id", unique = true, nullable = false)
	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Pay_type_id")
	public Paytype getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Paytype paytype) {
		this.paytype = paytype;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "service_date", length = 19)
	public Timestamp getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Timestamp serviceDate) {
		this.serviceDate = serviceDate;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
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

}