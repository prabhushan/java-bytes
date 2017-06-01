package com.prabhu.test;

public class ChildClass extends BaseClass {
	
	@Override
	public void method1(){
		System.out.println("Child class- method 1");
		super.method1();
	}
	
	@Override
	public void method2(){
		System.out.println("Child class- method 2");
	}
	
	public static void main(String[] args) {
		BaseClass c = new ChildClass();
		c.method1();
		c.method2();
	}

}
