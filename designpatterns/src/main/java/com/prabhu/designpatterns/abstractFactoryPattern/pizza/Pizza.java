package com.prabhu.designpatterns.abstractFactoryPattern.pizza;

public abstract class Pizza {

	String pizzaName;

	public Pizza(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public abstract void prepare();

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
