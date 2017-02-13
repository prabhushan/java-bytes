package com.java.bytes.practice.sort;

public class Factorial {
	
	public static void main(String...strings){
		System.out.println(new Factorial().factorial(5));
	}
	
	private int factorial(int n){
		if(n == 0 || n == 1){
			return 1;
		}
		return (n * factorial(n - 1));
	}

}
