package com.prabhu.designpatterns.abstractFactoryPattern;

import com.prabhu.designpatterns.abstractFactoryPattern.pizza.ChickenPizza;
import com.prabhu.designpatterns.abstractFactoryPattern.pizza.Pizza;
import com.prabhu.designpatterns.abstractFactoryPattern.pizza.VeggiePizza;

public class ChicagoPizzaStore extends PizzaStore{

	PizzaIngredientFactory ingredient = new ChicagoPizzaIngredientFactory();

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type=="veggie"){
			pizza = new VeggiePizza("Chi Veggie Pizza", ingredient);
		}
		else if(type=="chicken"){
			pizza = new ChickenPizza("Chi Chicken Pizza", ingredient);
		}
		return pizza;
	}
}
