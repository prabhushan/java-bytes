package com.prabhu.boot;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class NewComponent {
	
	@Autowired
	ConfigProperties config;
	
	
	
	public void printTest(){
		System.out.println(config.getHost() + config.getPort());
		System.out.println("Print Text without Component annotation");
	}

}
