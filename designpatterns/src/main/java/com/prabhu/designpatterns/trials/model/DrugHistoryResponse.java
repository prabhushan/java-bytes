package com.prabhu.designpatterns.trials.model;

public class DrugHistoryResponse {
	
	private Drug drug;
	private Pharmacy pharmacy;
	
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

}
