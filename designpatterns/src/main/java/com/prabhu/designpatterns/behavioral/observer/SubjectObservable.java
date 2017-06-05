package com.prabhu.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectObservable {

	List<IObserver> listObserver = new ArrayList<>();
	
	public void addObserver(IObserver observer){
		listObserver.add(observer);
	}
	
	public void statusChanged(){
		for (IObserver iObserver : listObserver) {
			iObserver.update();
		}
	}
	
}
