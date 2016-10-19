package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContactId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ContactId implements java.io.Serializable {

	// Fields

	private Integer homeId;
	private Integer contactId;
	private String name;

	// Constructors

	/** default constructor */
	public ContactId() {
	}

	/** full constructor */
	public ContactId(Integer homeId, Integer contactId, String name) {
		this.homeId = homeId;
		this.contactId = contactId;
		this.name = name;
	}

	// Property accessors

	@Column(name = "home_id")
	public Integer getHomeId() {
		return this.homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	@Column(name = "contact_id")
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ContactId))
			return false;
		ContactId castOther = (ContactId) other;

		return ((this.getHomeId() == castOther.getHomeId()) || (this
				.getHomeId() != null && castOther.getHomeId() != null && this
				.getHomeId().equals(castOther.getHomeId())))
				&& ((this.getContactId() == castOther.getContactId()) || (this
						.getContactId() != null
						&& castOther.getContactId() != null && this
						.getContactId().equals(castOther.getContactId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHomeId() == null ? 0 : this.getHomeId().hashCode());
		result = 37 * result
				+ (getContactId() == null ? 0 : this.getContactId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}