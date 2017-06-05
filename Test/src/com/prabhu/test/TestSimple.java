package com.prabhu.test;

public class TestSimple {

	public static void main(String[] args) {
		TestSimple t1 = new TestSimple();
		TestSimple t2 = new TestSimple();
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1.toString().equalsIgnoreCase(t2.toString()));
	}
	
	private String memberId;
	
	@Override
	public String toString() {
		
		return memberId+memberId; 
	}

}
