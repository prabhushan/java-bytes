package com.prabhu.designpatterns.behavioral.observer;

public interface IObserver {
	void update();
	void subscribe(SubjectObservable subject);
}
