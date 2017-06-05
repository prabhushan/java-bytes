package com.prabhu.annotations;

public class TransactionContext {
	
	static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	public static void setContext(String test) {
		threadLocal.set(test);
	}
	
	public static String getContext(){
		return threadLocal.get();
	}

}
