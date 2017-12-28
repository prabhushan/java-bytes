package com.prabhu.mockito;

public class Parent {
	
	private SpyTest childA;
	private MockTest childB;
	
	public String getSomething(String arg){
		return childB.getSomeValue(arg) + childA.getSomeValue(arg);
	}
	
	public MockTest getChildB() {
		return childB;
	}
	public void setChildB(MockTest childB) {
		this.childB = childB;
	}
	
	public Parent(SpyTest childA,MockTest childB) {
		this.childA = childA;
		this.childB = childB;
	}

}
