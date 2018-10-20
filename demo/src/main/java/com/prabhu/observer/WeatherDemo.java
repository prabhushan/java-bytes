package com.prabhu.observer;

import java.util.Observable;

public class WeatherDemo extends Observable {

	private String weather;

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public void updateWeather(String weather) {
		this.weather = weather;
		setChanged();
		notifyObservers(this);
	}

}
