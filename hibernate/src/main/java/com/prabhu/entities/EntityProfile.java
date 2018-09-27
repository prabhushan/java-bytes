package com.prabhu.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CURR_ENTITY_PROFILE")
// @Data
// @Builder
@NamedQuery(name = "getEntityProfile", query = "select c from EntityProfile c where entityProfileId=:id")
// @NoArgsConstructor
// @AllArgsConstructor

public class EntityProfile {

	@Id
	@Column(name = "ENTITY_PROFILE_ID", unique = true, nullable = false, precision = 22)
	private String entityProfileId;

	public String getEntityProfileId() {
		return entityProfileId;
	}

	public void setEntityProfileId(String entityProfileId) {
		this.entityProfileId = entityProfileId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public List<EntityProfileIdnty> getEntityProfileIdnty() {
		return entityProfileIdnty;
	}

	public void setEntityProfileIdnty(List<EntityProfileIdnty> entityProfileIdnty) {
		this.entityProfileIdnty = entityProfileIdnty;
	}

	@Column(name = "ENTITY_ID")
	private String entityId;

	@Column(name = "ACTION_FLAG")
	private String actionFlag;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "currEntityProfile")
	@JsonIgnoreProperties("currEntityProfile")
	private List<EntityProfileIdnty> entityProfileIdnty;

}
