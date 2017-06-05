package com.prabhu.threading;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

public class CallableTest implements Callable<String> {

	boolean fail;
	String id;

	public CallableTest(boolean shouldFail, String id) {
		this.fail = shouldFail;
		this.id = id;
	}


	public String call() throws Exception {
		System.out.println("Start time "+id+new Date());
		if (fail) {
			Thread.sleep(10000);
			System.out.println("Printing inside thread after waking");
//			new Exception("Some exception");
		}
		System.out.println("End time "+id+new Date());
		return "success-" + id;
	}


	
}
