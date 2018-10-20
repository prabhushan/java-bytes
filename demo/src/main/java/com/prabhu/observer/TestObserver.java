package com.prabhu.observer;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		WeatherDemo demo = (WeatherDemo) o;
		System.out.println(demo.getWeather());

	}

}
