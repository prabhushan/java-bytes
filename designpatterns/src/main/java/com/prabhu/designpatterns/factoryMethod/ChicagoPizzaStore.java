package com.prabhu.designpatterns.factoryMethod;

public class ChicagoPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type=="veggie"){
			pizza = new ChicagoStylePizza("Chicago Veggie");
		}
		else if(type=="chicken"){
			pizza = new ChicagoStylePizza("Chicago Chicken");
		}
		return pizza;
	}

}
