package com.prabhu.designpatterns.behavioral.observer;

public class ObservableB implements IObserver {

	@Override
	public void update() {
		System.out.println("Status B");

	}

	@Override
	public void subscribe(SubjectObservable subject) {
		subject.addObserver(this);
		
	}


}
