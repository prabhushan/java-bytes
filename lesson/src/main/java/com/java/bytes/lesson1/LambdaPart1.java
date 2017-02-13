package com.java.bytes.lesson1;

import java.util.Arrays;
import java.util.List;

/**
 * this reference - enclosed scope Lambda has no class.. different from closure.
 * Closure needs final or effective final
 * 
 * @author prabhu
 *
 */
public class LambdaPart1 {
	public static void main(String[] args) {
		List<String> listArray = Arrays.asList("sai", "baba", "prabhu", "test");

		listArray.forEach((s) -> System.out.println(s.toUpperCase()));
		System.out.println("###########");
		listArray.forEach(System.out::println);
		System.out.println("###########");
		listArray.forEach(LambdaPart1::printLowerCase);
		System.out.println("###########");
	}

	private static void printLowerCase(String s) {
		System.out.println(s.toLowerCase());
	}

	private void printInstance(String s) {
		System.out.println(s.toLowerCase());
	}
}
