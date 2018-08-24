package com.tista.aps.entity.ingestor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.tista.aps.entity.postprocessor.EntityResolutionIngester;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan({ "com.tista.aps.*" })
@Slf4j
public class EntityIngestorApplication implements CommandLineRunner {

	@Autowired
	private IdentityRepository customerRepository;

	@Autowired
	private EntityResolutionIngester entityResolutionIngester;

	private ExecutorService executorService;

	@Value("${application.thread.count}")
	private int threadCount;

	@PostConstruct
	private void preInitiaize() {
		executorService = Executors.newFixedThreadPool(threadCount);

	}

	public static void main(String[] args) {
		SpringApplication.run(EntityIngestorApplication.class, args);
	}

	@Override
	@Transactional(readOnly = true)
	public void run(String... arg0) throws Exception {
		try (Stream<Identity> identityStream = customerRepository.findIdentityIds()) {
			identityStream.forEach(this::postProcess);
		} finally {
			executorService.shutdown();

		}

	}

	private void postProcess(Identity s) {
		executorService.submit(() -> {
			try {
				entityResolutionIngester.ingestIdentity(s.getIdentityId());
			} catch (Exception e) {
				log.error("Unexpected error - ", e);
			}
		});
	}
}
