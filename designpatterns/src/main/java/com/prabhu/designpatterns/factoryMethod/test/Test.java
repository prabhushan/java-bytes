package com.prabhu.designpatterns.factoryMethod.test;

import com.prabhu.designpatterns.factoryMethod.ChicagoPizzaStore;
import com.prabhu.designpatterns.factoryMethod.NYPizzaStore;
import com.prabhu.designpatterns.factoryMethod.PizzaStore;

public class Test {

	public static void main(String[] args) {
		PizzaStore pizzaStore = new NYPizzaStore();
		pizzaStore.orderPizza("chicken");
		
		pizzaStore = new ChicagoPizzaStore();
		pizzaStore.orderPizza("chicken");

	}

}
