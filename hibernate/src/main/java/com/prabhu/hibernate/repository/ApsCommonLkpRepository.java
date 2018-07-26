package com.prabhu.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tista.aps.data.entity.current.ApsCommonLkp;
import com.tista.aps.data.entity.current.PrvdrTypeLkp;

public interface ApsCommonLkpRepository  extends JpaRepository<ApsCommonLkp, Long>{

	@Query("SELECT c FROM ApsCommonLkp c where type=:type")
	public List<ApsCommonLkp> findByType(@Param("type") String type);
	
	@Query("SELECT c FROM ApsCommonLkp c where type=:type and entityType=:entityType")
	public List<ApsCommonLkp> findByTypeAndEntityType(@Param("type") String type, @Param("entityType") String entityType);
	
	@Query("SELECT c FROM ApsCommonLkp c where category=:category and type=:type and entityType=:entityType")
	public ApsCommonLkp findByCategoryAndTypeAndEntityType(@Param("category") String category, @Param("type") String type, @Param("entityType") String entityType);

	
	@Query("SELECT c FROM ApsCommonLkp c where type=:type and code=:code")
	public ApsCommonLkp findByTypeAndCode(@Param("type") String type, @Param("code") String code);

	@Query("SELECT c FROM ApsCommonLkp c where category=:category and code=:code")
	public ApsCommonLkp findByCategoryAndCode(@Param("category") String category, @Param("code") String code);
	
	@Query("SELECT c FROM ApsCommonLkp c where category=:category and type=:type")
	public ApsCommonLkp findByCategoryAndType(@Param("category") String category, @Param("type") String type);
	
	
	@Query("SELECT c FROM PrvdrTypeLkp c where formCode=:formCode and providerCode =:providerCode and entityType=:entityType")
	public PrvdrTypeLkp findProviderTypeByProviderTypeCd(@Param("formCode") String formCode, @Param("providerCode") String providerCode, @Param("entityType") String entityType);
}
