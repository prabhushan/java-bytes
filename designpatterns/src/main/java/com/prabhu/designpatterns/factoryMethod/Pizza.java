package com.prabhu.designpatterns.factoryMethod;

public abstract class Pizza {

	String pizzaName;

	public Pizza(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public void prepare() {
		System.out.println("preparing --> "+ this.pizzaName);
	}

	public void bake() {
		System.out.println("bake");
	}

	public void cut() {
		System.out.println("cut");
	}

	public void box() {
		System.out.println("box");
	}
}
