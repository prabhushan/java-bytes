package com.tista.aps.entity.ingestor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.tista.aps.entity.postprocessor.ERServiceCaller;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntityIngestor {

	@Autowired
	private IdentityRepository identityRepository;

	@Autowired
	private ERServiceCaller erServiceCaller;

	private ExecutorService executorService;

	@Value("${application.thread.count}")
	private int threadCount;

	@PostConstruct
	private void preInitiaize() {
		executorService = Executors.newFixedThreadPool(threadCount);

	}

	@Transactional(readOnly = true)
	public void process() throws InterruptedException {
		StopWatch stopWatch = new StopWatch("Main execution");
		stopWatch.start("Main execution");
		try (Stream<Identity> identityStream = identityRepository.findIdentityIds()) {
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
				MDC.put("identity-id", s.getIdentityId());
				erServiceCaller.resolveIdentity(s.getIdentityId());
			} finally {
				log.info("Total time taken for ER in ms --" + (System.nanoTime() - startTime) / 1000_000);
				MDC.clear();
			}
		});
	}
}
