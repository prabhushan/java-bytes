package com.prabhu.designpatterns.creational.builder;

/**
 * Builder pattern Used when - The class can take multiple constructors -
 * telescopic style Some of the parameters can be optional
 * 
 * earlier design approaches 1) multiple constructors 2) multiple setter
 * methods. But object is created in intermediate state which is wrong
 * 
 * @author prabhu
 *
 */
public class Builder {
	public static void main(String[] args) {
		System.out.println(new Nutrition.NutritionBuilder(1, 2, 3).build().toString());
	}

}
