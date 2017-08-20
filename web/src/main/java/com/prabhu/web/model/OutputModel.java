package com.prabhu.web.model;

public class OutputModel extends Base{
	
	private String outputName;
	private String outputPhone;
	public String getOutputPhone() {
		return outputPhone;
	}
	public void setOutputPhone(String outputPhone) {
		this.outputPhone = outputPhone;
	}
	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	public OutputModel(String name,String phone) {
    this.outputName = name;
    this.outputPhone = phone;
	}


}
