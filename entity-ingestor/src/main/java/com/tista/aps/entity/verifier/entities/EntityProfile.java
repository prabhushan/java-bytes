package com.tista.aps.entity.verifier.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CURR_ENTITY_PROFILE")
@Data
public class EntityProfile {

	@Id
	@Column(name = "ENTITY_PROFILE_ID", unique = true, nullable = false, precision = 22)
	private String entityProfileId;

	@OneToMany
	@JoinColumn(name = "ENTITY_PROFILE_ID")
	private List<EntityProfileIdnty> listIdentities;

}
