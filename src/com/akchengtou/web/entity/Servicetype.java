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
 * Servicetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "servicetype", catalog = "ak_zhsq")
@JsonIgnoreProperties(value={"members"})
public class Servicetype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private Paytype paytype;
	private String name;
	private Float price;
	private String content;
	private String image;
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors

	/** default constructor */
	public Servicetype() {
	}

	/** full constructor */
	public Servicetype(Paytype paytype, String name, Float price,
			String content, String image, Set<Member> members) {
		this.paytype = paytype;
		this.name = name;
		this.price = price;
		this.content = content;
		this.image = image;
		this.members = members;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_type_id")
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

	@Column(name = "price", precision = 12, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "image", length = 100)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}