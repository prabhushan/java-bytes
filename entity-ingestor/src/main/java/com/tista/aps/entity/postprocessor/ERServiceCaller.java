package com.tista.aps.entity.postprocessor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ERServiceCaller {

	final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${er-service}")
	private String erServiceUrl;

	public HttpResponse<String> resolveIdentity(String identityId) {
		log.info("Calling ER started");
		String payLoad = null;
		try {
			payLoad = objectMapper
					.writeValueAsString(ProfileEntity.builder().inputIdentitiesList(Arrays.asList(identityId)).build());
			return makeHttpCall(payLoad);
		} catch (Exception e) {
			log.error("Payload" + payLoad, e);
		} finally {
			log.info("Calling ER completed -");
		}

		return null;

	}

	private HttpResponse<String> makeHttpCall(String payLoad) throws UnirestException {
		return Unirest.post(erServiceUrl).header("Content-Type", "application/json").body(payLoad).asString();

	}

}
