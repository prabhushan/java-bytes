package com.prabhu.designpatterns.command.receiver;

public class Light {

	String name;
	public Light(String name){
		this.name = name;
	}
	public void on(){
		System.out.println(name + "-" + "Light On");
	}
	
	public void off(){
		System.out.println(name + "-" + "Light Off");
	}
}
