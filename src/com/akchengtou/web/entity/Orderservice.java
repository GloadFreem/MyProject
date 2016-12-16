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
 * Orderservice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderservice", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"ordercomments"})
public class Orderservice implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Ordertype ordertype;
	private Service service;
	private String orderCode; //订单编号
	private Orderstatus orderstatus;
	private Date orderDate;
	private String erCode;
	private short price;
	private Set<Ordercomment> ordercomments = new HashSet<Ordercomment>(0);

	// Constructors

	/** default constructor */
	public Orderservice() {
	}

	/** full constructor */
	public Orderservice(Ordertype ordertype, Service service,
			Orderstatus orderstatus, Date orderDate, String erCode,
			Set<Ordercomment> ordercomments,short price) {
		this.ordertype = ordertype;
		this.service = service;
		this.orderstatus = orderstatus;
		this.orderDate = orderDate;
		this.erCode = erCode;
		this.ordercomments = ordercomments;
		this.price = price;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	public Ordertype getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(Ordertype ordertype) {
		this.ordertype = ordertype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_id")
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	@Column(name = "order_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "er_code")
	public String getErCode() {
		return this.erCode;
	}

	public void setErCode(String erCode) {
		this.erCode = erCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderservice")
	public Set<Ordercomment> getOrdercomments() {
		return this.ordercomments;
	}

	public void setOrdercomments(Set<Ordercomment> ordercomments) {
		this.ordercomments = ordercomments;
	}

	/**
	 * @return the orderCode
	 */
	@Column(name="order_code")
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the price
	 */
	@Column(name="price")
	public short getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(short price) {
		this.price = price;
	}

}