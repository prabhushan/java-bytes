package com.prabhu.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author bkondalraj
 *
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
