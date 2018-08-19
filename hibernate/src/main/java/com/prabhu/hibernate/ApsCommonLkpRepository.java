package com.prabhu.hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApsCommonLkpRepository extends JpaRepository<ApsCommonLkp, Long> {

	@Query("SELECT c FROM ApsCommonLkp c where type=:type")
	public List<ApsCommonLkp> findByType(@Param("type") String type);

	@Query("SELECT c FROM ApsCommonLkp c where type=:type and entityType=:entityType")
	public List<ApsCommonLkp> findByTypeAndEntityType(@Param("type") String type,
			@Param("entityType") String entityType);

}
