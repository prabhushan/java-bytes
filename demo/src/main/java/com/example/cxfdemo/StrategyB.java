package com.example.cxfdemo;

import org.springframework.stereotype.Component;

@Component("stratB")
public class StrategyB implements IStrategy {

	@Override
	public void printSomething() {
		System.out.println("StrategyB");

	}

}
