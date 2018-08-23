package com.tista.aps.entity.ingestor;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan({ "com.tista.aps.*" })
public class EntityIngestorApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(EntityIngestorApplication.class, args);
	}

	@Override
	@Transactional(readOnly = true)
	public void run(String... arg0) throws Exception {
		try (Stream<Identity> identityStream = customerRepository.findIdentityIds()) {
			identityStream.forEach(System.out::println);
		}

	}
}
