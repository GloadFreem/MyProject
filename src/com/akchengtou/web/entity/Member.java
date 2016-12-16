package com.akchengtou.web.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"tasks","services","user"})
public class Member implements java.io.Serializable {

	// Fields

	private Integer memberId;
	private User user;
	private Servicetype servicetype;
	private String name;
	private String telephone;
	private String image;
	private Double gender;
	private Set<Service> services = new HashSet<Service>(0);

	// Constructors

	/** default constructor */
	public Member() {
	}

	/** full constructor */
	public Member(User user, 
			Servicetype servicetype, String name, String telephone,
			String image, Double gender, Set<Service> services) {
		this.user = user;
		this.servicetype = servicetype;
		this.name = name;
		this.telephone = telephone;
		this.image = image;
		this.gender = gender;
		this.services = services;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id", unique = true, nullable = false)
	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_type_id")
	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", length = 11)
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

	@Column(name = "gender", precision = 22, scale = 0)
	public Double getGender() {
		return this.gender;
	}

	public void setGender(Double gender) {
		this.gender = gender;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Service> getServices() {
		return this.services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

}