package com.prabhu.designpatterns.behavioral.observer;

public class ObservableA implements IObserver {

	@Override
	public void update() {
		System.out.println("Status A");

	}

	@Override
	public void subscribe(SubjectObservable subject) {
		subject.addObserver(this);
		
	}

}
