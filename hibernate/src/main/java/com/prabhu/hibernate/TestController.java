package com.prabhu.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private ApsCommonLkpRepository apsRepo;

	@RequestMapping(value = "/test/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List<ApsCommonLkp> cacheRepoLookup(@PathVariable("type") String type) {
		return apsRepo.findByType(type);
	}

}
