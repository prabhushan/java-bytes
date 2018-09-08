package com.prabhu.threads;

public class Person {

	private String name;
	private int age;
	private String gender;

	public String getName() {
		System.out.println("getName");
		return name;
	}

	public int getAge() {
		System.out.println("getAge " + name + " Thread - " + Thread.currentThread());
		return age;
	}

	public String getGender() {
		System.out.println("getGender " + name + " Thread - " + Thread.currentThread());
		return gender;
	}

	Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "name-" + name + "  Age -" + age + "  Gender -" + gender;
	}

}
