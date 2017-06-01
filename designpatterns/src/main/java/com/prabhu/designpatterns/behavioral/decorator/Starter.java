package com.prabhu.designpatterns.behavioral.decorator;

public class Starter {

	public static void main(String[] args) {
		Beverage coffee = new ConcreteCoffee();
		Beverage coffee1 = new CondimentsB(new CondimentsA(coffee));
		Beverage coffee2 = new CondimentsA(new CondimentsA(coffee));
		Beverage coffee3 = new CondimentsB(new CondimentsB(coffee));
		System.out.println(coffee1.cost() + "-"+coffee1.getDescription());
		System.out.println(coffee2.cost() + "-"+coffee2.getDescription());
		System.out.println(coffee3.cost() + "-"+coffee3.getDescription());

	}

}
