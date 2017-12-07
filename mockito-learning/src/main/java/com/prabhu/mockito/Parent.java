package com.prabhu.mockito;

public class Parent {
	
	private ChildA childA;
	private ChildB childB;
	
	public String getSomething(String arg){
		return childB.getSomeValue(arg) + childA.getSomeValue(arg);
	}
	
	public ChildB getChildB() {
		return childB;
	}
	public void setChildB(ChildB childB) {
		this.childB = childB;
	}
	
	public Parent(ChildA childA,ChildB childB) {
		this.childA = childA;
		this.childB = childB;
	}

}
