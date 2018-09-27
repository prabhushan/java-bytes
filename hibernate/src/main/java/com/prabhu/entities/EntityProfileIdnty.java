package com.prabhu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Builder
@Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Data
@Table(name = "CURR_ENTITY_PROFILE_IDNTY")
public class EntityProfileIdnty {

	public String getEntityProfileIdntyId() {
		return entityProfileIdntyId;
	}

	public void setEntityProfileIdntyId(String entityProfileIdntyId) {
		this.entityProfileIdntyId = entityProfileIdntyId;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public EntityProfile getCurrEntityProfile() {
		return currEntityProfile;
	}

	public void setCurrEntityProfile(EntityProfile currEntityProfile) {
		this.currEntityProfile = currEntityProfile;
	}

	@Id
	@Column(name = "ENTITY_PROFILE_IDNTY_ID")
	private String entityProfileIdntyId;

	@Column(name = "ACTION_FLAG")
	private String actionFlag;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTITY_PROFILE_ID")
	@JsonIgnoreProperties("entityProfileIdnty")
	private EntityProfile currEntityProfile;

}
