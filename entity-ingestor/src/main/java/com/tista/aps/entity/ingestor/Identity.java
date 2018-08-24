package com.tista.aps.entity.ingestor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "curr_identity")
@Data
public class Identity {
	@Id
	@Column(name = "IDENTITY_ID", unique = true, nullable = false, precision = 22)
	private String identityId;

	@Column(name = "SOURCE_ID")
	private String sourceID;

}
