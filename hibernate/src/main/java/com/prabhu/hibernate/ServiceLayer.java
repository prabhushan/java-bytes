package com.prabhu.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prabhu.entities.EntityProfile;
import com.prabhu.entities.EntityProfileIdnty;
import com.prabhu.entities.EntityProfileIdntyRespository;
import com.prabhu.entities.EntityProfileRespository;
import com.prabhu.entities.ReportLcmIdentity;

@Service

public class ServiceLayer {

	@Autowired
	EntityProfileIdntyRespository entityRepo;

	@Autowired
	EntityProfileRespository repo;

	@Autowired
	ReportRepository reportRepo;

	@Autowired
	EntityManagerFactory entityFactory;

	@Autowired
	EntityManager em;

	public EntityProfile getCurrIdentities(String profileId) {
		EntityProfile ep = (EntityProfile) entityFactory.createEntityManager().createNamedQuery("getEntityProfile")
				.setParameter("id", profileId).getResultList().get(0);

		return ep;
	}

	public List<ReportLcmIdentity> getReportLCM(String reportUniqueID) {

		return reportRepo.findReports(reportUniqueID);
	}

	public EntityProfileIdnty getCurrEntityProfileIdentity(String entityProfileIdentityId) {
		return entityRepo.getEntityProfileIdnty(entityProfileIdentityId);

	}

	@Transactional
	public void persistEntityProfile() {

		// System.out.println("entered entity profile");
		// EntityManager em = entityFactory.createEntityManager();
		// EntityTransaction et = em.getTransaction();
		// et.begin();
		// EntityProfile ep = new
		// EntityProfile().builder().actionFlag("D").entityId("10000001")
		// .entityProfileId("2323232324").build();
		//
		//// EntityProfileIdnty epi = new
		// EntityProfileIdnty().builder().entityProfileIdntyId("2000000").actionFlag("D")
		//// .currEntityProfile(ep).build();
		// em.persist(ep);
		// em.persist(epi);
		// et.commit();
		// EntityProfile ep = new
		// EntityProfile().builder().actionFlag("D").entityId("10000001")
		// .entityProfileId("2323232324").build();
		//
		//// EntityProfileIdnty epi = new
		// EntityProfileIdnty().builder().entityProfileIdntyId("2000000").actionFlag("D")
		//// .currEntityProfile(ep).build();

		test();
	}

	public void test() {

		// EntityProfile ep = new
		// EntityProfile().builder().actionFlag("D").entityId("testprabhu29")
		// .entityProfileId("2323232331").build();
		// repo.save(ep);
		//
		// EntityProfile ep1 = new
		// EntityProfile().builder().actionFlag("D").entityId("testprabhu")
		// .entityProfileId("2323232328").build();
		// repo.save(ep1);

	}

}
