package com.prabhu.searcher;

public class SearchCriteria {
	private String fieldName;
	private String fieldValue;
	private String operator;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public SearchCriteria(String name,String value,String operator) {
		this.fieldName = name;
		this.fieldValue = value;
		this.operator = operator;
	}

}
