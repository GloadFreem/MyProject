package com.akchengtou.web.entity;

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
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member", catalog = "ak_zhsq")
public class Member implements java.io.Serializable {

	// Fields

	private Integer memberId;
	private User user;
	private Employertype employertype;
	private String name;
	private String telephone;
	private String image;
	private Double gender;
	private Set<Service> services = new HashSet<Service>(0);

	// Constructors

	/** default constructor */
	public Member() {
	}

	/** minimal constructor */
	public Member(Integer memberId) {
		this.memberId = memberId;
	}

	/** full constructor */
	public Member(Integer memberId, User user, Employertype employertype,
			String name, String telephone, String image, Double gender,
			Set<Service> services) {
		this.memberId = memberId;
		this.user = user;
		this.employertype = employertype;
		this.name = name;
		this.telephone = telephone;
		this.image = image;
		this.gender = gender;
		this.services = services;
	}

	// Property accessors
	@Id
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id")
	public Employertype getEmployertype() {
		return this.employertype;
	}

	public void setEmployertype(Employertype employertype) {
		this.employertype = employertype;
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