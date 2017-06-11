package com.prabhu.designpatterns.abstractFactoryPattern;

import com.prabhu.designpatterns.abstractFactoryPattern.pizza.ChickenPizza;
import com.prabhu.designpatterns.abstractFactoryPattern.pizza.Pizza;
import com.prabhu.designpatterns.abstractFactoryPattern.pizza.VeggiePizza;

public class NYPizzaStore extends PizzaStore {

	PizzaIngredientFactory ingredient = new NYPizzaIngredientFactory();
	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type=="veggie"){
			pizza = new VeggiePizza("NY Veggie Pizza", ingredient);
		}
		else if(type=="chicken"){
			pizza = new ChickenPizza("NY Chicken Pizza", ingredient);
		}
		return pizza;
	}

}
