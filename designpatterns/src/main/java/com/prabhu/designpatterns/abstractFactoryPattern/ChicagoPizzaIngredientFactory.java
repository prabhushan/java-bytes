package com.prabhu.designpatterns.abstractFactoryPattern;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public String createDough() {
		// TODO Auto-generated method stub
		return "Chicago Dough";
	}

	@Override
	public String createCheese() {
		// TODO Auto-generated method stub
		return "Chicago Cheese";
	}

	@Override
	public String createClam() {
		// TODO Auto-generated method stub
		return "Chicago Clam";
	}

	@Override
	public String createVeggies() {
		// TODO Auto-generated method stub
		return "Chicago Veggies";
	}

	@Override
	public String createChicken() {
		// TODO Auto-generated method stub
		return "Chicago Chicken";
	}

}
