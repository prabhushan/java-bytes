package com.prabhu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "REPORT_LCM_ENRMNT_MAPPING")
public class ReportLcmEnrollmentIdentity {

	@Id
	@Column(name = "REPORT_LCM_ENRMNT_ID")
	private String enrollmentKey;

	@ManyToOne
	@JoinColumn(name = "ENTITY_ID")
	private ReportLcmIdentity reportLCMidentity;

	public ReportLcmIdentity getReportLCMidentity() {
		return reportLCMidentity;
	}

	public void setReportLCMidentity(ReportLcmIdentity reportLCMidentity) {
		this.reportLCMidentity = reportLCMidentity;
	}

	@Column(name = "CONTRACTOR_ID")
	private String contractorId;

	@Transient
	private String contractorName;

	@Column(name = "ENROLLMENT_ID")
	private String enrollmentId;

	@Column(name = "ENROLLMENT_STATE")
	private String enrollmentState;

	@Column(name = "ENROLLMENT_STATUS")
	private String enrollmentStatus;

	@Column(name = "ENTITY_PROFILE_ID")
	private String entityProfileId;

	@Column(name = "TYPE")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// public String getEntityId() {
	// return entityId;
	// }
	//
	// public void setEntityId(String entityId) {
	// this.entityId = entityId;
	// }

	public String getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(String enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public String getEnrollmentState() {
		return enrollmentState;
	}

	public void setEnrollmentState(String enrollmentState) {
		this.enrollmentState = enrollmentState;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public String getEntityProfileId() {
		return entityProfileId;
	}

	public void setEntityProfileId(String entityProfileId) {
		this.entityProfileId = entityProfileId;
	}
}