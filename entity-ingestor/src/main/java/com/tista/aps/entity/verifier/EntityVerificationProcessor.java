package com.tista.aps.entity.verifier;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tista.aps.entity.verifier.entities.EntityProfile;
import com.tista.aps.entity.verifier.entities.EntityProfileRespository;

@Service
public class EntityVerificationProcessor {

	@Autowired
	private EntityProfileRespository entityProfileRepo;

	@Transactional(readOnly = true)
	public void process() {
		try (Stream<EntityProfile> entityProfileStream = entityProfileRepo.findEntityProfiles()) {
			entityProfileStream.forEach(System.out::println);
		}
	}

}
