package com.tista.aps.entity.postprocessor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntityResolutionIngester {

	final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${er-service}")
	private String erServiceUrl;

	public void ingestIdentity(String identityId) {
		log.info("Calling ER started -" + identityId);
		String payLoad = null;
		try {
			payLoad = objectMapper
					.writeValueAsString(ProfileEntity.builder().inputIdentitiesList(Arrays.asList(identityId)).build());
			makeHttpCall(payLoad);
		} catch (Exception e) {
			log.error("Identity" + identityId + "Payload" + payLoad, e);
		}

		log.info("Calling ER completed -" + identityId);

	}

	private HttpResponse<JsonNode> makeHttpCall(String payLoad) throws UnirestException {
		return Unirest.post(erServiceUrl).header("Content-Type", "application/json").body(payLoad).asJson();

	}

}
