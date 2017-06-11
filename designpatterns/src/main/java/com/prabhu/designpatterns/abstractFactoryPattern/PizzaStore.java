package com.prabhu.designpatterns.abstractFactoryPattern;

import com.prabhu.designpatterns.abstractFactoryPattern.pizza.Pizza;

public abstract class PizzaStore {
	//factory method
	protected abstract Pizza createPizza(String type);
	
	public Pizza orderPizza(String type){
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

}
