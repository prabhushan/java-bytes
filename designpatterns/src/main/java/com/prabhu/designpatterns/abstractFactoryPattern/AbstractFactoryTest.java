package com.prabhu.designpatterns.abstractFactoryPattern;

import com.prabhu.designpatterns.abstractFactoryPattern.pizza.Pizza;

public class AbstractFactoryTest {

	public static void main(String[] args) {
		PizzaStore store = new NYPizzaStore();
		Pizza pizza = store.createPizza("veggie");
		AbstractFactoryTest.steps(pizza);
		
		pizza = store.createPizza("chicken");
		AbstractFactoryTest.steps(pizza);
		
		store = new ChicagoPizzaStore();
		pizza = store.createPizza("veggie");
		AbstractFactoryTest.steps(pizza);
		
		pizza = store.createPizza("chicken");
		AbstractFactoryTest.steps(pizza);
	}
	
	public String printWorld(){
		return "Hello World";
	}
	
	public void print(){
		System.out.println("Hello");
	}
	
	
	public String getString(){
		return "test";
	}
	
	private static void steps(Pizza pizza){
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
	}
}
