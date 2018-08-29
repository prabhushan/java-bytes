package com.tista.aps.entity.verifier;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tista.aps.entity.postprocessor.ERServiceCaller;
import com.tista.aps.entity.postprocessor.ProfileEntity;
import com.tista.aps.entity.verifier.entities.AnalyzeModel;
import com.tista.aps.entity.verifier.entities.EntityProfile;
import com.tista.aps.entity.verifier.entities.EntityProfileIdnty;
import com.tista.aps.entity.verifier.entities.EntityProfileRespository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntityVerificationProcessor {

	final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	private ExecutorService executorService;

	@Value("${application.thread.count}")
	private int threadCount;

	@Value("${report.filename}")
	private String fileName;

	private File reportFile;

	@PostConstruct
	private void preInitiaize() {
		executorService = Executors.newFixedThreadPool(1);
		reportFile = new File(fileName);

	}

	@Autowired
	private ERServiceCaller erServiceCaller;

	@Autowired
	private EntityProfileRespository entityProfileRepo;

	@Transactional(readOnly = true)
	public void process() throws InterruptedException {
		try (Stream<EntityProfile> entityProfileStream = entityProfileRepo.findEntityProfiles()) {
			entityProfileStream.forEach(this::processERResponse);
		} finally {
			executorService.shutdown();
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

		}
	}

	private void processERResponse(EntityProfile entityProfile) {
		executorService.submit(() -> {
			try {
				ProfileEntity profileEntity = processResponse(
						erServiceCaller.resolveIdentity(entityProfile.getEntityProfileId()));
				log.info("Call done");
				constructAnalyzeModel(profileEntity, entityProfile);
				log.info("Analyze done");
			} catch (IOException e) {
				log.error("Unexpected Exception -", e);
			}
		});

	}

	private void constructAnalyzeModel(ProfileEntity profileEntity, EntityProfile entityProfile) throws IOException {
		log.info("Entered constructAnalyzeModel");
		ReportGenerator.generateReport(reportFile,
				AnalyzeModel.builder().profileEntityId(entityProfile.getEntityProfileId())
						.currentIdentityIds(getListEntityProfileIdnty(entityProfile.getListEntityPofileIdentities()))
						.resolvedidentityIds(getListAsString(profileEntity.getMatchedIdentitiesList())).build()
						.toString());
		log.info("Exit constructAnalyzeModel");
	}

	private ProfileEntity processResponse(HttpResponse<String> response) throws IOException {
		return objectMapper.readValue(response.getBody(), ProfileEntity.class);
	}

	private String getListEntityProfileIdnty(List<EntityProfileIdnty> listEntityPofileIdentities) {
		StringBuilder strBuilder = new StringBuilder();
		for (EntityProfileIdnty entityProfileIdntyt : listEntityPofileIdentities) {
			if (entityProfileIdntyt != null && StringUtils.isNotBlank(entityProfileIdntyt.getIdentityID())) {
				strBuilder.append(entityProfileIdntyt.getIdentityID()).append("|");
			}
		}

		return strBuilder.toString();
	}

	private String getListAsString(List<String> listResolvedIdentities) {
		StringBuilder strBuilder = new StringBuilder();
		for (String entityProfileIdntyt : listResolvedIdentities) {
			if (StringUtils.isNotBlank(entityProfileIdntyt)) {
				strBuilder.append(entityProfileIdntyt).append("|");
			}
		}

		return strBuilder.toString();
	}

}
