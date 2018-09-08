package com.prabhu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CURR_ENTITY_PROFILE_IDNTY")
public class EntityProfileIdnty {

	@Id
	@Column(name = "ENTITY_PROFILE_IDNTY_ID")
	private String idntyId;

	@Column(name = "IDENTITY_ID")
	private String identityID;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

}
