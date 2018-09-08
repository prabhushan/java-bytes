package com.prabhu.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURR_ENTITY_PROFILE")
@Data
@Builder
@NamedQuery(name = "getEntityProfile", query = "select c from EntityProfile c where entityProfileId=:id")
@NoArgsConstructor
@AllArgsConstructor
public class EntityProfile {

	@Id
	@Column(name = "ENTITY_PROFILE_ID", unique = true, nullable = false, precision = 22)
	private String entityProfileId;

	@OneToMany
	@JoinColumn(name = "ENTITY_PROFILE_ID")
	private List<EntityProfileIdnty> listEntityPofileIdentities;

}
