package com.prabhu.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.prabhu.*" })
public class HibernateApplication {

	@Autowired
	ServiceLayer sl;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	// @Override
	// public void run(String... arg0) throws Exception {
	// sl.persistEntityProfile();
	//
	// }
}
