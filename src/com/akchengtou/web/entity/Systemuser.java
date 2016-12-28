package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Systemuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "systemuser", catalog = "ak_zhsq")
public class Systemuser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer roleTypeId;
	private String password;
	private String account;

	// Constructors

	/** default constructor */
	public Systemuser() {
	}

	/** full constructor */
	public Systemuser(Integer roleTypeId, String password, String account) {
		this.roleTypeId = roleTypeId;
		this.password = password;
		this.account = account;
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

	@Column(name = "role_type_id")
	public Integer getRoleTypeId() {
		return this.roleTypeId;
	}

	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "account")
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}