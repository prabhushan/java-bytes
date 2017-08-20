package com.java.bytes.test;

public class DrugHistory {
	private String drugName;
	private String dose;
	private String someCode;
	
	public DrugHistory(String drugName,String dose,String someCode) {
		this.drugName = drugName;
		this.dose = dose;
		this.someCode = someCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getSomeCode() {
		return someCode;
	}
	public void setSomeCode(String someCode) {
		this.someCode = someCode;
	}
	
	@Override
	public String toString() {
		
		return this.drugName+this.dose+this.someCode;
	}

}
