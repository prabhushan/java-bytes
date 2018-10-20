package com.prabhu.observer;

public class Test {

	public static void main(String[] args) {
		WeatherDemo w1 = new WeatherDemo("nice weather");
		WeatherDemo w2 = new WeatherDemo("Hot weather");
		TestObserver t1 = new TestObserver();
		TestObserver t2 = new TestObserver();
		TestObserver t3 = new TestObserver();

		w1.addObserver(t1);
		w1.addObserver(t2);
		w1.addObserver(t3);

	}

}
