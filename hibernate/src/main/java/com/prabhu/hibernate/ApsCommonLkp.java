package com.prabhu.hibernate;

import java.beans.ConstructorProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "APS_COMMON_LKP")
@Where(clause = "ACTIVE_IND='A'")
public class ApsCommonLkp {
	@Id
	@Column(name = "APS_COMMON_LKP_ID")
	private long id;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "CODE")
	private String code;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "ENTITY_TYPE")
	private String entityType;
	@Column(name = "ACTIVE_IND")
	private String activeInd;

	@ConstructorProperties({ "id", "category", "type", "code", "description", "entityType", "activeInd" })
	public ApsCommonLkp(long id, String category, String type, String code, String description, String entityType,
			String activeInd) {
		this.id = id;
		this.category = category;
		this.type = type;
		this.code = code;
		this.description = description;
		this.entityType = entityType;
		this.activeInd = activeInd;
	}

	public ApsCommonLkp() {
	}

	public long getId() {
		return this.id;
	}

	public String getCategory() {
		return this.category;
	}

	public String getType() {
		return this.type;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public String getEntityType() {
		return this.entityType;
	}

	public String getActiveInd() {
		return this.activeInd;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}

	public String toString() {
		return "ApsCommonLkp(id=" + this.getId() + ", category=" + this.getCategory() + ", type=" + this.getType()
				+ ", code=" + this.getCode() + ", description=" + this.getDescription() + ", entityType="
				+ this.getEntityType() + ", activeInd=" + this.getActiveInd() + ")";
	}
}