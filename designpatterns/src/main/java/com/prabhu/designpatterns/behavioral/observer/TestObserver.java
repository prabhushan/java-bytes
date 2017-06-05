package com.prabhu.designpatterns.behavioral.observer;

public class TestObserver {

	public static void main(String[] args) {
		SubjectObservable subject = new SubjectObservable();
		
		IObserver observableA = new ObservableA();
		observableA.subscribe(subject);
		
		IObserver observableB = new ObservableB();
		observableB.subscribe(subject);

		subject.statusChanged();
	}

}
