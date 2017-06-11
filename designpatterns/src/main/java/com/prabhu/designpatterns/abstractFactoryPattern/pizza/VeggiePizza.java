package com.prabhu.designpatterns.abstractFactoryPattern.pizza;

import com.prabhu.designpatterns.abstractFactoryPattern.PizzaIngredientFactory;


public class VeggiePizza extends Pizza {

	PizzaIngredientFactory ingrFactory;
	public VeggiePizza(String pizzaName,PizzaIngredientFactory ingrFactory) {
		super(pizzaName);
		this.ingrFactory = ingrFactory;
	}
	@Override
	public void prepare() {
		System.out.println("Preparing--");
		System.out.println(this.ingrFactory.createDough());
		System.out.println(this.ingrFactory.createVeggies());
		
	}

}
