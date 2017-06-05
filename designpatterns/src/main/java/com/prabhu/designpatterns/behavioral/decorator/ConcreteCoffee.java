package com.prabhu.designpatterns.behavioral.decorator;

public class ConcreteCoffee extends Beverage{

	@Override
	public int cost() {
		return 10;
	}

	@Override
	public String getDescription() {
		return "Concrete Coffee";
	}

}
