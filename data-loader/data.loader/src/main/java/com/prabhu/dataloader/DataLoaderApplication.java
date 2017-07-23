package com.prabhu.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DataLoaderApplication implements CommandLineRunner {

	@Autowired
	DataLoaderDao loaderDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DataLoaderApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		loaderDao.exportData();
		
	}
	
	
	
	
	
	
}
