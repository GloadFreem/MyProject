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
 * Orderservice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderservice", catalog = "ak_zhsq")
public class Orderservice implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Ordertype ordertype;
	private Service service;
	private Orderstatus orderstatus;
	private Timestamp orderDate;
	private String erCode;
	private Set<Ordercomment> ordercomments = new HashSet<Ordercomment>(0);

	// Constructors

	/** default constructor */
	public Orderservice() {
	}

	/** minimal constructor */
	public Orderservice(Integer orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public Orderservice(Integer orderId, Ordertype ordertype, Service service,
			Orderstatus orderstatus, Timestamp orderDate, String erCode,
			Set<Ordercomment> ordercomments) {
		this.orderId = orderId;
		this.ordertype = ordertype;
		this.service = service;
		this.orderstatus = orderstatus;
		this.orderDate = orderDate;
		this.erCode = erCode;
		this.ordercomments = ordercomments;
	}

	// Property accessors
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Ordertype getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(Ordertype ordertype) {
		this.ordertype = ordertype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	@Column(name = "order_date", length = 19)
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "er_code")
	public String getErCode() {
		return this.erCode;
	}

	public void setErCode(String erCode) {
		this.erCode = erCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderservice")
	public Set<Ordercomment> getOrdercomments() {
		return this.ordercomments;
	}

	public void setOrdercomments(Set<Ordercomment> ordercomments) {
		this.ordercomments = ordercomments;
	}

}