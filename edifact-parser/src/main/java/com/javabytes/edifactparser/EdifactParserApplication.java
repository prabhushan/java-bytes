package com.javabytes.edifactparser;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.javabytes.edifact.version.ParserFactory;

@SpringBootApplication
@ComponentScan(basePackages={"com.javabytes.edifact.version","com.javabytes.edifactparser"})
public class EdifactParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdifactParserApplication.class, args);
	}
	
	@Bean
	public FactoryBean serviceLocatorFactoryBean() {
	    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
	    factoryBean.setServiceLocatorInterface(ParserFactory.class);
	    return factoryBean;
	 }
	
}
