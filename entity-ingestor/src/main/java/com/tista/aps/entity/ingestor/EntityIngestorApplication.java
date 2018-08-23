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

@SpringBootApplication
@ComponentScan({ "com.tista.aps.*" })
public class EntityIngestorApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	EntityResolutionIngester entityResolutionIngester;

	ExecutorService executorService;

	@Value("${application.thread.count}")
	int threadCount;

	@PostConstruct
	void preInitiaize() {
		executorService = Executors.newFixedThreadPool(threadCount);

	}

	public static void main(String[] args) {
		SpringApplication.run(EntityIngestorApplication.class, args);
	}

	@Override
	@Transactional(readOnly = true)
	public void run(String... arg0) throws Exception {
		try (Stream<Identity> identityStream = customerRepository.findIdentityIds()) {
			identityStream.forEach(s -> postProcess(s));
		} finally {
			executorService.shutdown();

		}

	}

	private void postProcess(Identity s) {
		executorService.submit(() -> {
			try {
				entityResolutionIngester.ingestIdentity(s.getIdentityId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
