package com.prabhu.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntityProfileIdntyRespository extends JpaRepository<EntityProfileIdnty, Long> {

	@Query("SELECT c FROM EntityProfileIdnty c where entityProfileIdntyId=:identityId")
	public EntityProfileIdnty getEntityProfileIdnty(@Param("identityId") String entityProfileIdentityId);
}
