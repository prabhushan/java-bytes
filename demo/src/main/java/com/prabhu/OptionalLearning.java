package com.prabhu;

import java.util.Optional;

public class OptionalLearning {

	public static void main(String[] args) {
		Optional<String> strOptional = Optional.ofNullable("SAI");
		Optional<String> nullOptional = Optional.ofNullable(null);
		nullOptional.map(s -> s.toLowerCase()).ifPresent(System.out::println);
		// strOptional.flatMap(s -> Optional.of(s.toLowerCase()));
	}

	// public static void main(String[] args) {
	// Optional<String> s = Optional.of("input");
	// System.out.println(s.map(Test::getOutput));
	// System.out.println(s.flatMap(Test::getOutputOpt));
	// }
	//
	// static Optional<String> getOutputOpt(String input) {
	// return input == null ? Optional.empty() : Optional.of("output for " + input);
	// }
	//
	// static String getOutput(String input) {
	// return input == null ? null : "output for " + input;
	// }

}
