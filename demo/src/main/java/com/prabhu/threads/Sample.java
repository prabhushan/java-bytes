package com.prabhu.threads;

import java.util.Arrays;
import java.util.List;

public class Sample {

	static List<Person> listPersons = Arrays.asList(new Person("prabhu", 21, "m"), new Person("Lucy", 30, "f"),
			new Person("testA", 5, "m"), new Person("Susan", 79, "f"), new Person("sai", 45, "f"));

	/**
	 * Key points to notice Stream does not execute a function on the collection of
	 * data. It executes collectio of function on a data. getAge and getGender is
	 * executed on each data - sequential. Rest of the elements is not touched
	 * 
	 * in parallel - all the data is touched.
	 * 
	 * @param strings
	 */
	public static void main(String... strings) {
		listPersons.stream().parallel().filter(s -> s.getAge() > 20).filter(s -> s.getGender() == "f")
				.map(s -> s.getName().toUpperCase()).findAny().ifPresent(s -> System.out.println(s));
	}

}
