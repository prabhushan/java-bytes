package com.tista.aps.entity.ingestor;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Identity, Long> {

	@Query("SELECT c FROM Identity c order by UPDATED_DATE asc")
	public Stream<Identity> findIdentityIds();

}
