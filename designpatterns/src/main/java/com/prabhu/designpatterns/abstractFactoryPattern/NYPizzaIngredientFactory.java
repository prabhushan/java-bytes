package com.prabhu.designpatterns.abstractFactoryPattern;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public String createDough() {
		return "NY Dough";
	}

	@Override
	public String createCheese() {
		return "NY Cheese";
	}

	@Override
	public String createClam() {
		return "NY Clam";
	}

	@Override
	public String createVeggies() {
		return "NY Veggies";
	}

	@Override
	public String createChicken() {
		
		return "NY chicken";
	}

}
