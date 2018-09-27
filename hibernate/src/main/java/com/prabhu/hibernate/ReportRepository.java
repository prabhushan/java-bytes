package com.prabhu.hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prabhu.entities.ReportLcmIdentity;

public interface ReportRepository extends JpaRepository<ReportLcmIdentity, String> {

	@Query("SELECT c FROM ReportLcmIdentity c where c.reportId=:reportId")
	public List<ReportLcmIdentity> findReports(@Param("reportId") String reportId);

}
