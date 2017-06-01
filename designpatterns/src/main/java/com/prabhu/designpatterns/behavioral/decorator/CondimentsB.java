package com.prabhu.designpatterns.behavioral.decorator;

public class CondimentsB extends CondimentsDecorator {

	Beverage beverage;
	public CondimentsB(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public int cost() {
		
		return 2+beverage.cost();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Condiments B,"+beverage.getDescription();
	}

}
