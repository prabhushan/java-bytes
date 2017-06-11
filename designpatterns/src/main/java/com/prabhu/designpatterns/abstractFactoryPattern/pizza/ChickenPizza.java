package com.prabhu.designpatterns.abstractFactoryPattern.pizza;

import com.prabhu.designpatterns.abstractFactoryPattern.PizzaIngredientFactory;


public class ChickenPizza extends Pizza {

	PizzaIngredientFactory ingrFactory;
	public ChickenPizza(String pizzaName,PizzaIngredientFactory ingrFactory) {
		super(pizzaName);
		this.ingrFactory = ingrFactory;
	}
	@Override
	public void prepare() {
		System.out.println("Preparing--");
		System.out.println(this.ingrFactory.createDough());
		System.out.println(this.ingrFactory.createChicken());
		
	}

}
