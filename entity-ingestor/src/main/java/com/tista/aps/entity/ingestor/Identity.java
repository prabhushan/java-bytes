package com.tista.aps.entity.ingestor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "curr_identity")
@Data
public class Identity {
	@Id
	@SequenceGenerator(name = "IDENTITY_ID_GENERATOR", sequenceName = "CURR_IDENTITY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDENTITY_ID_GENERATOR")
	@Column(name = "IDENTITY_ID", unique = true, nullable = false, precision = 22)
	private long identityId;

	@Column(name = "SOURCE_ID")
	private String sourceID;

}
