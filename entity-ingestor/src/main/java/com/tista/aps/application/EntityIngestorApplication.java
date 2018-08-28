package com.tista.aps.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tista.aps.entity.ingestor.EntityIngestor;
import com.tista.aps.entity.verifier.EntityVerificationProcessor;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = { "com.tista.aps.*" })
@EnableJpaRepositories(basePackages = { "com.tista.aps.*" })
@EntityScan(basePackages = { "com.tista.aps.*" })

@Slf4j
public class EntityIngestorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EntityIngestorApplication.class, args);
	}

	@Autowired
	private EntityIngestor entityIngestor;

	@Autowired
	private EntityVerificationProcessor entityVerificationProcessor;

	@Override
	public void run(String... arg0) throws Exception {
		if (arg0 == null || arg0.length == 0) {
			log.error("Input argument is important - ingest or analyze");
		}
		if ("ingest".equals(arg0[0])) {
			log.info("Started Entity Ingestor");
			entityIngestor.process();
		} else {
			log.info("Started Analyzer");
			entityVerificationProcessor.process();

		}
	}

}
