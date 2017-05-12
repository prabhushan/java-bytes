package com.prabhu.test;

public class SampleTest {

	public int a = 5;

	public void doSomething() {
		final int b = 10;
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(a+10);
				System.out.println(b+10);

				a = a + 1;
			}
		};
		Thread t = new Thread(runnable);
		t.start();
	}
	
	public static void main(String...strings){
		SampleTest test = new SampleTest();
		test.doSomething();
		System.out.println("outer method --> "+test.a);
	}

}
