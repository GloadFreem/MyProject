package com.akchengtou.web.entity;

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
 * Employertype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employertype", catalog = "ak_zhsq")
public class Employertype implements java.io.Serializable {

	// Fields

	private Integer employerId;
	private String name;
	private Boolean valid;
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors

	/** default constructor */
	public Employertype() {
	}

	/** minimal constructor */
	public Employertype(Integer employerId) {
		this.employerId = employerId;
	}

	/** full constructor */
	public Employertype(Integer employerId, String name, Boolean valid,
			Set<Member> members) {
		this.employerId = employerId;
		this.name = name;
		this.valid = valid;
		this.members = members;
	}

	// Property accessors
	@Id
	@Column(name = "employer_id", unique = true, nullable = false)
	public Integer getEmployerId() {
		return this.employerId;
	}

	public void setEmployerId(Integer employerId) {
		this.employerId = employerId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "valid")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employertype")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}