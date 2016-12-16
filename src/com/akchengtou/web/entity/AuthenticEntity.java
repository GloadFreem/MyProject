package com.akchengtou.web.entity;

import java.io.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class AuthenticEntity {
	@Min(value=1,message="{Pattern.authentic.cityId.notnull}")
	private Integer houseId = 0;
	@NotNull(message="{Pattern.user.reaalname.notnull}")
	private String name;
	
	private String idCard;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
