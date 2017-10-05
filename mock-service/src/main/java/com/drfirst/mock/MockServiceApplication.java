package com.drfirst.mock;


import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockServiceApplication implements CommandLineRunner {

    private static Logger log = Logger.getLogger(MockServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MockServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("test");
		
	}
}
