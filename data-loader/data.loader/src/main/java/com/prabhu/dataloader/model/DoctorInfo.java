package com.prabhu.dataloader.model;

import java.util.Map;

public class DoctorInfo {

	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Map<String, String> getDoctorDetails() {
		return doctorDetails;
	}
	public void setDoctorDetails(Map<String, String> doctorDetails) {
		this.doctorDetails = doctorDetails;
	}
	private String speciality;
	private Map<String,String> doctorDetails;
	
}
