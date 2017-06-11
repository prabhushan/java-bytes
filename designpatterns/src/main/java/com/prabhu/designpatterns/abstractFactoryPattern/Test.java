package com.prabhu.designpatterns.abstractFactoryPattern;

import com.prabhu.designpatterns.abstractFactoryPattern.pizza.Pizza;

public class Test {

	public static void main(String[] args) {
		PizzaStore store = new NYPizzaStore();
		Pizza pizza = store.createPizza("veggie");
		Test.steps(pizza);
		
		pizza = store.createPizza("chicken");
		Test.steps(pizza);
		
		store = new ChicagoPizzaStore();
		pizza = store.createPizza("veggie");
		Test.steps(pizza);
		
		pizza = store.createPizza("chicken");
		Test.steps(pizza);
	}
	
	private static void steps(Pizza pizza){
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
	}
}
