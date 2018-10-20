package com.prabhu;

import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		Stream<Integer> stm = Stream.of(10, 30, 20, 40);
		stm.forEach(System.out::println);

		stm.forEach(System.out::println);
	}

}
