package com.example.cxfdemo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestStrategy {

	@Autowired
	Map<String, IStrategy> listStratgies;

	public String toString() {

		listStratgies.get("stratB").printSomething();
		return "stratB";
	}

}
