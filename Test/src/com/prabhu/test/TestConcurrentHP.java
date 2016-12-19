package com.prabhu.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConcurrentHP {/*

	//static volatile Map<Object, Object> testMap = new ConcurrentHashMap<>();
	static volatile Map<Object, Object> testMap = Collections.synchronizedMap(new HashMap<Object, Object>());

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.submit(()->printIfMapEmpty());
		}

		executorService.shutdown();

	}

	protected static void printIfMapEmpty() {
		if (testMap.isEmpty()) {
			synchronized (testMap) {
				if (testMap.isEmpty()) {
					System.out.println("Map is Empty "+Thread.currentThread().getName());
					testMap.put(UUID.randomUUID().toString(), 100);
					System.out.println("Map is  Empty " + testMap.size());

				} else {
					System.out.println("Map is NOT Empty " + testMap.size());

				}

			}
		}
	}
*/}
