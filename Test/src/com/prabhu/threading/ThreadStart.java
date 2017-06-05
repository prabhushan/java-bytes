package com.prabhu.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadStart {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<String> future = executor.submit(new Callable<String>() {

			public String call() throws Exception {
				try{
					Thread.sleep(5000);
					System.out.println("Inside thread");
					//throw new NullPointerException("insider exception");
					return "hello world";
				}
				catch(InterruptedException ex){
					System.out.println("Interrupted inside thread");
				}
				return null;
				
			}

		});

		try {
			System.out.println(future.get(5000,TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("Cancelling thread");
			future.cancel(false);
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}



}
