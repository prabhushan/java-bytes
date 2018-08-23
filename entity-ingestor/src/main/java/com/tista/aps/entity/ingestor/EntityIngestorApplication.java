package com.tista.aps.entity.ingestor;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.tista.aps.*" })
public class EntityIngestorApplication implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(EntityIngestorApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println(customerRepository.findIdentityIds(33828580L));

	}
}
