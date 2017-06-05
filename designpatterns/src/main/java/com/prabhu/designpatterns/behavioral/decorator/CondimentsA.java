package com.prabhu.designpatterns.behavioral.decorator;

public class CondimentsA extends CondimentsDecorator {

	Beverage beverage;
	
	public CondimentsA(Beverage beverage) {
		this.beverage = beverage;
	}
	@Override
	public int cost() {
		
		return 1+beverage.cost();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Condiments A,"+beverage.getDescription();
	}

}
