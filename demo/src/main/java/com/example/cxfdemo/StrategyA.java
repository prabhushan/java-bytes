package com.example.cxfdemo;

import org.springframework.stereotype.Component;

@Component("stratA")
public class StrategyA implements IStrategy {

	@Override
	public void printSomething() {
		System.out.println("StrategyA");

	}

}
