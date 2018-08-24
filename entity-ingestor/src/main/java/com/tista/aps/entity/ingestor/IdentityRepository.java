package com.tista.aps.entity.ingestor;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IdentityRepository extends JpaRepository<Identity, Long> {

	@Query("SELECT c FROM Identity c where ACTION_FLAG = 'A' order by UPDATED_DATE asc")
	public Stream<Identity> findIdentityIds();

}
