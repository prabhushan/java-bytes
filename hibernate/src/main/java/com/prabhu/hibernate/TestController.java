package com.prabhu.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prabhu.entities.EntityProfile;
import com.prabhu.entities.EntityProfileIdnty;
import com.prabhu.entities.ReportLcmIdentity;

@RestController
public class TestController {

	@Autowired
	private ApsCommonLkpRepository apsRepo;

	@Autowired
	private ServiceLayer service;

	@RequestMapping(value = "/test/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List<ApsCommonLkp> cacheRepoLookup(@PathVariable("type") String type) {
		return apsRepo.findByType(type);
	}

	@RequestMapping(value = "/getIdentity/{profileId}", method = RequestMethod.GET)
	@ResponseBody
	public EntityProfile fetchEntityProfile(@PathVariable("profileId") String profileId) {
		EntityProfile ep = service.getCurrIdentities(profileId);

		return ep;
	}

	@RequestMapping(value = "/getReport/{reportID}", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportLcmIdentity> fetchReport(@PathVariable("reportID") String reportID) {
		List<ReportLcmIdentity> list = service.getReportLCM(reportID);
		return list;
	}

	@RequestMapping(value = "/fetchEntityProfile/{entityProfileIdentityId}", method = RequestMethod.GET)
	@ResponseBody
	public EntityProfileIdnty fetchEntityProfileIdentityId(
			@PathVariable("entityProfileIdentityId") String entityProfileIdentityId) {
		return service.getCurrEntityProfileIdentity(entityProfileIdentityId);
		// return null;// service.getCurrEntityProfileIdentity(entityProfileIdentityId);
	}
}
