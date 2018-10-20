package com.prabhu.threads;

import java.util.List;
import java.util.stream.Stream;

public class DemoFunctionalInterface {

	public static void main(String[] args) {

		Stream<Integer> nS = Stream.of(5, 6, 8);

		List<Integer> oS = nS.filter(n -> n % 2 == 1).toList();
		System.out.println(oS.size());
		Stream<Integer> oS1 = nS.filter(n -> n % 2 == 1);
		System.out.println(oS1.count());
		List<Integer> oS2 = nS.filter(n -> n % 2 == 1).collect();
		System.out.println(oS2.size());
		Stream<Integer> oS3 = nS.allMatch(n -> n % 2 == 1);
		System.out.println(oS3.count());
	}

}
