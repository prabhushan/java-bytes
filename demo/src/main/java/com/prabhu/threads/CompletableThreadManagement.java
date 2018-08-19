package com.prabhu.threads;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class CompletableThreadManagement {

	public static void main(String[] args) throws Exception {
		// GracefulshutdownSpringApplication.run(CompletableThreadManagement.class,
		// args);
		run(null);
	}

	public static void run(String... arg0) throws Exception {
		System.out.println(Runtime.getRuntime().availableProcessors());
		ForkJoinPool pool = ForkJoinPool.commonPool();
		System.out.println(pool);
		CompletableFuture.supplyAsync(CompletableThreadManagement::getProcessOrder)
				.thenApply(CompletableThreadManagement::processOrder).thenAccept(o -> System.out.println(o)).get();
		System.out.println("DONE" + Thread.currentThread());

	}

	private static String processOrder(Stream<String> s) {
		s.parallel().mapToInt(a -> Integer.parseInt(a) * 2).forEach(a -> {
			try {
				System.out.println(a + "--->" + Thread.currentThread());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("---");
		});
		return "test";
	}

	private static Stream<String> getProcessOrder() {
		return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"1", "2", "3", "4", "5", "6", "7", "8", "9").stream();
	}

	// @Override
	// public void run(String... arg0) throws Exception {
	// CompletableFuture.supplyAsync(CompletableThreadManagement::getOrder)
	// .thenAccept(CompletableThreadManagement::processOrder);
	//
	// }
	//
	// private static String processOrder(String s) {
	// System.out.println("Process Order Thread #" + " " + Thread.currentThread());
	// return s;
	// }
	//
	// private static String getOrder() {
	// System.out.println("Thread #" + " " + Thread.currentThread());
	// // return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
	// return "100";
	// }
}
