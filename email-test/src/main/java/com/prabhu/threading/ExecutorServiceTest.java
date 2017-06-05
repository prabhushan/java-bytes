package com.prabhu.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		CallableTest testA = new CallableTest(true, "waps");
		CallableTest testB = new CallableTest(false, "nehii");
		Collection<CallableTest> collectionTask = new ArrayList<CallableTest>();
		collectionTask.add(testA);
		collectionTask.add(testB);

			List<Future<String>> listFuture = null;
				System.out.println("before invoking time -->"+new Date());
				listFuture = executorService.invokeAll(collectionTask, 5000, TimeUnit.MILLISECONDS);
				System.out.println("after invoking time -->"+new Date());
				for (Future<String> oneFuture : listFuture) {
					String result = null;

					if (oneFuture != null && !oneFuture.isCancelled()) {
						System.out.println("received time "+new Date());
						try {
							System.out.println(oneFuture.get());
						} catch (ExecutionException e) {
							System.out.println("Exception inside For loop");
							e.printStackTrace();
						}

					}

					
				}
	
			
			
		}
		
	

}
