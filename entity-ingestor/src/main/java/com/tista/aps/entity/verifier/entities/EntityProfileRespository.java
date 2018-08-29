package com.tista.aps.entity.verifier.entities;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EntityProfileRespository extends JpaRepository<EntityProfile, Long> {

	@Query("SELECT c FROM EntityProfile c where ACTION_FLAG = 'A' and rownum < 10 order by UPDATED_DATE asc")
	public Stream<EntityProfile> findEntityProfiles();
}
