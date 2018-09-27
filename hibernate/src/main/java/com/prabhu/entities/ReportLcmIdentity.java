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
import javax.persistence.Transient;

@Entity
@Table(name = "REPORT_LICENSE_ALERT")
@NamedQuery(name = "getLicense", query = "select c from ReportLcmIdentity c where c.reportId=:reportId")

public class ReportLcmIdentity {

	@Id
	@Column(name = "REPORT_UNIQUE_ID")
	private String reportId;

	@Column(name = "MANUAL_REVIEW_IND")
	private String reviewed;

	@Column(name = "CREATED_DATE")
	private String lcmReportReadyDate;

	@Column(name = "LIC_STATUS_ALERT")
	private String lcmLicenseStatus;

	@Column(name = "LIC_QUALIFIER_ALERT")
	private String lcmLicenseQualifier;

	@Column(name = "APS_NPI")
	private String lcmNpi;

	@Column(name = "APS_FIRSTNAME")
	private String lcmFirstName;

	@Column(name = "APS_LASTNAME")
	private String lcmLastName;

	@Column(name = "LICENSE_NUMBER")
	private String lcmLicenseNumber;

	@Column(name = "LICENSE_EXPIRATION_DATE")
	private String lcmLicenseExpirationDate;

	@Column(name = "LICENSE_STATUS")
	private String lcmHmsLicenseStatus;

	@Column(name = "LICENSE_QUALIFIER")
	private String lcmHmsLicenseQualifier;

	@Column(name = "ALERT_DATE")
	private String lcmAlertDate;

	@Column(name = "SCREEN_DATE")
	private String lcmDailyScreenDate;

	@Transient
	private String lcmAlertDateOrDailyScreenDate;

	@Transient
	private String lcmMacAgreesWithFindings;

	@Transient
	private String lcmMacDateReviewed;

	@Transient
	private String lcmDateMacTookAction;

	@Transient
	private String lcmMacAction;

	@Transient
	private String lcmNotesIssues;

	@Column(name = "EFFECTIVE_DATE")
	private String lcmOriginalReportWeekStartDate;

	@Column(name = "END_DATE")
	private String lcmOriginalReportWeekEndDate;

	@Transient
	private String lcmOriginalReportWeek;

	@Transient
	private String lcmApsSource;

	// @Formula("SELECT URL FROM CURR_IDENTITY_ENROLLMENT CIE WHERE CIE.ENTITY_ID =
	// entityId")
	@Transient
	private String lcmEntityProfileLink;

	@Column(name = "ENTITY_ID")
	private String entityId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "reportLCMidentity")
	// @JoinColumn(name = "ENTITY_ID")
	List<ReportLcmEnrollmentIdentity> enrollmentList;

	// @OneToMany(fetch = FetchType.EAGER, targetEntity =
	// ReportLcmEnrollmentIdentity.class, mappedBy = "entityId",
	// cascade=CascadeType.ALL)
	// @Where(clause = "TYPE = 'ASSOCIATION'")
	// List<ReportLcmEnrollmentIdentity> associatedEnrollmentList = new
	// ArrayList<ReportLcmEnrollmentIdentity>();

	public List<ReportLcmEnrollmentIdentity> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<ReportLcmEnrollmentIdentity> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	public String getLcmAlertDate() {
		return lcmAlertDate;
	}

	public void setLcmAlertDate(String lcmAlertDate) {
		this.lcmAlertDate = lcmAlertDate;
	}

	public String getLcmDailyScreenDate() {
		return lcmDailyScreenDate;
	}

	public void setLcmDailyScreenDate(String lcmDailyScreenDate) {
		this.lcmDailyScreenDate = lcmDailyScreenDate;
	}

	public String getLcmOriginalReportWeekStartDate() {
		return lcmOriginalReportWeekStartDate;
	}

	public void setLcmOriginalReportWeekStartDate(String lcmOriginalReportWeekStartDate) {
		this.lcmOriginalReportWeekStartDate = lcmOriginalReportWeekStartDate;
	}

	public String getLcmOriginalReportWeekEndDate() {
		return lcmOriginalReportWeekEndDate;
	}

	public void setLcmOriginalReportWeekEndDate(String lcmOriginalReportWeekEndDate) {
		this.lcmOriginalReportWeekEndDate = lcmOriginalReportWeekEndDate;
	}

	// public String getEntityId() {
	// return entityId;
	// }
	//
	// public void setEntityId(String entityId) {
	// this.entityId = entityId;
	// }

	// public List<ReportLcmEnrollmentIdentity> getAssociatedEnrollmentList() {
	// return associatedEnrollmentList;
	// }
	//
	// public void setAssociatedEnrollmentList(List<ReportLcmEnrollmentIdentity>
	// associatedEnrollmentList) {
	// this.associatedEnrollmentList = associatedEnrollmentList;
	// }

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReviewed() {
		return reviewed;
	}

	public void setReviewed(String reviewed) {
		this.reviewed = reviewed;
	}

	public String getLcmReportReadyDate() {
		return lcmReportReadyDate;
	}

	public void setLcmReportReadyDate(String lcmReportReadyDate) {
		this.lcmReportReadyDate = lcmReportReadyDate;
	}

	public String getLcmLicenseStatus() {
		return lcmLicenseStatus;
	}

	public void setLcmLicenseStatus(String lcmLicenseStatus) {
		this.lcmLicenseStatus = lcmLicenseStatus;
	}

	public String getLcmLicenseQualifier() {
		return lcmLicenseQualifier;
	}

	public void setLcmLicenseQualifier(String lcmLicenseQualifier) {
		this.lcmLicenseQualifier = lcmLicenseQualifier;
	}

	public String getLcmNpi() {
		return lcmNpi;
	}

	public void setLcmNpi(String lcmNpi) {
		this.lcmNpi = lcmNpi;
	}

	public String getLcmFirstName() {
		return lcmFirstName;
	}

	public void setLcmFirstName(String lcmFirstName) {
		this.lcmFirstName = lcmFirstName;
	}

	public String getLcmLastName() {
		return lcmLastName;
	}

	public void setLcmLastName(String lcmLastName) {
		this.lcmLastName = lcmLastName;
	}

	public String getLcmLicenseNumber() {
		return lcmLicenseNumber;
	}

	public void setLcmLicenseNumber(String lcmLicenseNumber) {
		this.lcmLicenseNumber = lcmLicenseNumber;
	}

	public String getLcmLicenseExpirationDate() {
		return lcmLicenseExpirationDate;
	}

	public void setLcmLicenseExpirationDate(String lcmLicenseExpirationDate) {
		this.lcmLicenseExpirationDate = lcmLicenseExpirationDate;
	}

	public String getLcmHmsLicenseStatus() {
		return lcmHmsLicenseStatus;
	}

	public void setLcmHmsLicenseStatus(String lcmHmsLicenseStatus) {
		this.lcmHmsLicenseStatus = lcmHmsLicenseStatus;
	}

	public String getLcmHmsLicenseQualifier() {
		return lcmHmsLicenseQualifier;
	}

	public void setLcmHmsLicenseQualifier(String lcmHmsLicenseQualifier) {
		this.lcmHmsLicenseQualifier = lcmHmsLicenseQualifier;
	}

	public String getLcmAlertDateOrDailyScreenDate() {
		return lcmAlertDateOrDailyScreenDate;
	}

	public void setLcmAlertDateOrDailyScreenDate(String lcmAlertDateOrDailyScreenDate) {
		this.lcmAlertDateOrDailyScreenDate = lcmAlertDateOrDailyScreenDate;
	}

	public String getLcmMacAgreesWithFindings() {
		return lcmMacAgreesWithFindings;
	}

	public void setLcmMacAgreesWithFindings(String lcmMacAgreesWithFindings) {
		this.lcmMacAgreesWithFindings = lcmMacAgreesWithFindings;
	}

	public String getLcmMacDateReviewed() {
		return lcmMacDateReviewed;
	}

	public void setLcmMacDateReviewed(String lcmMacDateReviewed) {
		this.lcmMacDateReviewed = lcmMacDateReviewed;
	}

	public String getLcmDateMacTookAction() {
		return lcmDateMacTookAction;
	}

	public void setLcmDateMacTookAction(String lcmDateMacTookAction) {
		this.lcmDateMacTookAction = lcmDateMacTookAction;
	}

	public String getLcmMacAction() {
		return lcmMacAction;
	}

	public void setLcmMacAction(String lcmMacAction) {
		this.lcmMacAction = lcmMacAction;
	}

	public String getLcmNotesIssues() {
		return lcmNotesIssues;
	}

	public void setLcmNotesIssues(String lcmNotesIssues) {
		this.lcmNotesIssues = lcmNotesIssues;
	}

	public String getLcmOriginalReportWeek() {
		return lcmOriginalReportWeek;
	}

	public void setLcmOriginalReportWeek(String lcmOriginalReportWeek) {
		this.lcmOriginalReportWeek = lcmOriginalReportWeek;
	}

	public String getLcmApsSource() {
		return lcmApsSource;
	}

	public void setLcmApsSource(String lcmApsSource) {
		this.lcmApsSource = lcmApsSource;
	}

	public String getLcmEntityProfileLink() {
		return lcmEntityProfileLink;
	}

	public void setLcmEntityProfileLink(String lcmEntityProfileLink) {
		this.lcmEntityProfileLink = lcmEntityProfileLink;
	}
}