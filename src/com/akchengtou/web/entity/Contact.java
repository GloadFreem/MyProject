package com.akchengtou.web.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contact", catalog = "ak_zhsq")
public class Contact implements java.io.Serializable {

	// Fields

	private ContactId id;
	private Homehouse homehouse;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** minimal constructor */
	public Contact(ContactId id) {
		this.id = id;
	}

	/** full constructor */
	public Contact(ContactId id, Homehouse homehouse) {
		this.id = id;
		this.homehouse = homehouse;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "homeId", column = @Column(name = "home_id")),
			@AttributeOverride(name = "contactId", column = @Column(name = "contact_id")),
			@AttributeOverride(name = "name", column = @Column(name = "name")) })
	public ContactId getId() {
		return this.id;
	}

	public void setId(ContactId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_id", insertable = false, updatable = false)
	public Homehouse getHomehouse() {
		return this.homehouse;
	}

	public void setHomehouse(Homehouse homehouse) {
		this.homehouse = homehouse;
	}

}