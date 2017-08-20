package com.prabhu.web.model;

public class OutputModel2 extends Base{
	
	private String test;
	private String newModel;
	
	public OutputModel2(String test,String newModel) {
		this.test = test;
		this.newModel = newModel;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getNewModel() {
		return newModel;
	}
	public void setNewModel(String newModel) {
		this.newModel = newModel;
	}
}
