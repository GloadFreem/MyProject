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
 * Propertycharges entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "propertycharges", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"user"})
public class Propertycharges implements java.io.Serializable {

	// Fields

	private Integer chargeId;
	private User user;
	private String name;
	private Date endDate;
	private Double price;
	private String status;
	private Set<Charge> charges = new HashSet<Charge>(0);

	// Constructors

	/** default constructor */
	public Propertycharges() {
	}

	/** full constructor */
	public Propertycharges(User user, String name, Date endDate, Double price,
			String status, Set<Charge> charges) {
		this.user = user;
		this.name = name;
		this.endDate = endDate;
		this.price = price;
		this.status = status;
		this.charges = charges;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "charge_id", unique = true, nullable = false)
	public Integer getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "end_date", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "propertycharges")
	public Set<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}

}