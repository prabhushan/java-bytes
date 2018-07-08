package com.service.model;

public class InputFields {
	
	private int recordNumber;
	private String fieldA;
	private String fieldB;
	private String fieldC;
	private String fieldValue;
	
	public String getFieldA() {
		return fieldA;
	}
	public void setFieldA(String fieldA) {
		this.fieldA = fieldA;
	}
	public String getFieldB() {
		return fieldB;
	}
	public void setFieldB(String fieldB) {
		this.fieldB = fieldB;
	}
	public String getFieldC() {
		return fieldC;
	}
	public void setFieldC(String fieldC) {
		this.fieldC = fieldC;
	}
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	public InputFields(int recordNumber,String fieldA,String fieldB, String fieldC) {
		this.recordNumber = recordNumber;
		this.fieldA = fieldA;
		this.fieldB = fieldB;
		this.fieldC = fieldC;
	}
	
	@Override
   public String toString(){
	return recordNumber + "~"+fieldA+"~"+fieldB+"~"+fieldC;
	   
   }
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}
