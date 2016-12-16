package com.akchengtou.web.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContenttypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ContenttypeId implements java.io.Serializable {

	// Fields

	private Integer contentId;
	private Integer typeId;

	// Constructors

	/** default constructor */
	public ContenttypeId() {
	}

	/** full constructor */
	public ContenttypeId(Integer contentId, Integer typeId) {
		this.contentId = contentId;
		this.typeId = typeId;
	}

	// Property accessors

	@Column(name = "content_id", nullable = false)
	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	@Column(name = "type_id", nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ContenttypeId))
			return false;
		ContenttypeId castOther = (ContenttypeId) other;

		return ((this.getContentId() == castOther.getContentId()) || (this
				.getContentId() != null && castOther.getContentId() != null && this
				.getContentId().equals(castOther.getContentId())))
				&& ((this.getTypeId() == castOther.getTypeId()) || (this
						.getTypeId() != null && castOther.getTypeId() != null && this
						.getTypeId().equals(castOther.getTypeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getContentId() == null ? 0 : this.getContentId().hashCode());
		result = 37 * result
				+ (getTypeId() == null ? 0 : this.getTypeId().hashCode());
		return result;
	}

}