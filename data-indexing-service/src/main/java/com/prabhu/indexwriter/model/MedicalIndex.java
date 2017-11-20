package com.prabhu.indexwriter.model;

public class MedicalIndex
{

    private ListPracticeDetails[] listPracticeDetails;

    private Id _id;

    private DoctorDetails doctorDetails;

    private String speciality;

	public ListPracticeDetails[] getListPracticeDetails() {
		return listPracticeDetails;
	}

	public void setListPracticeDetails(ListPracticeDetails[] listPracticeDetails) {
		this.listPracticeDetails = listPracticeDetails;
	}

	public Id get_id() {
		return _id;
	}

	public void set_id(Id _id) {
		this._id = _id;
	}

	public DoctorDetails getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(DoctorDetails doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


}