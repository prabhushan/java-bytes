package com.prabhu.designpatterns.factoryMethod;

public class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type=="veggie"){
			pizza = new NYVeggiePizza("New York Veggie");
		}
		else if(type=="chicken"){
			pizza = new NYChickenPizza("New York Chicken");
		}
		return pizza;
	}

}
