package com.prabhu.boot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootLessonApplication {

	@Autowired
	NewComponent newComponent;
	
	@Autowired
	ExternalConfig externalConfig;

	@Bean
	public NewComponent initialize(){
		return new NewComponent();
	}
	
	@ConfigurationProperties(prefix="external")
	@Bean
	public ExternalConfig config(){
		return new ExternalConfig();
	}
	
	@RequestMapping("/boot")
	public String testController(){
		newComponent.printTest();
		
		return "Hello Boot";
	}
	
	@PostConstruct
	public void init(){
		System.out.println(externalConfig.getConfigText());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLessonApplication.class, args);
	}
}
