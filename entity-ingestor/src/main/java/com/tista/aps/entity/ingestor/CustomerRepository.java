package com.tista.aps.entity.ingestor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Identity, Long> {

	@Query("SELECT c FROM Identity c where IDENTITY_ID = :identityId order by UPDATED_DATE asc")
	public Identity findIdentityIds(@Param("identityId") Long identityId);

}
