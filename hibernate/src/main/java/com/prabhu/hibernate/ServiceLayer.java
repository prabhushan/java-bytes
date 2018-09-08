package com.prabhu.hibernate;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prabhu.entities.EntityProfile;
import com.prabhu.entities.EntityProfileRespository;

@Service
public class ServiceLayer {

	@Autowired
	EntityProfileRespository entityRepo;

	@Autowired
	EntityManagerFactory entityFactory;

	public List getCurrIdentities(String profileId) {
		EntityProfile entityProfile = EntityProfile.builder().entityProfileId(profileId).build();
		return entityFactory.createEntityManager().createNamedQuery("getEntityProfile").setParameter("id", profileId)
				.getResultList();

	}

}
