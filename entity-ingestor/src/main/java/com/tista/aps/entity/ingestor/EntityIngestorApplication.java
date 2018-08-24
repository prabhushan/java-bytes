package com.tista.aps.entity.ingestor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

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
		StopWatch stopWatch = new StopWatch("Main execution");
		stopWatch.start("Main execution");
		try (Stream<Identity> identityStream = customerRepository.findIdentityIds()) {
			identityStream.forEach(this::postProcess);
		} finally {
			executorService.shutdown();
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
			stopWatch.stop();
			log.info("Total time taken for execution -" + stopWatch.prettyPrint());
		}

	}

	private void postProcess(Identity s) {
		executorService.submit(() -> {
			long startTime = System.nanoTime();
			try {
				entityResolutionIngester.ingestIdentity(s.getIdentityId());
			} finally {
				log.info("Total time taken in ms --" + (System.nanoTime() - startTime) / 1000_000);
			}
		});
	}
}
