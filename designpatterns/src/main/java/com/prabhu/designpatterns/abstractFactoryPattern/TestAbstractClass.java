package com.prabhu.designpatterns.abstractFactoryPattern;

public abstract class TestAbstractClass {

	public void voidTest() {
		System.out.println("Print Void Test");
	}

	public int returnInt() {
		return 0;

	}

	public String updateString(String params) {
		return params+"appended";
	}

	public TestAbstractClass(String test) {
		
	}


}
