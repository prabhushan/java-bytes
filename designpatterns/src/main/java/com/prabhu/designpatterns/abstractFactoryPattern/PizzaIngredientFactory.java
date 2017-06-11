package com.prabhu.designpatterns.abstractFactoryPattern;

//This is abstract factory
public interface PizzaIngredientFactory {

	String createDough();
	String createCheese();
	String createClam();
	String createVeggies();
	String createChicken();
	
}
