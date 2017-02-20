package com.prabhu.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Path("/myresource")
@RestController
public class MyResource {

	@RequestMapping("/greeting")
	public String name() {
		return "hello World";
		
	}
}
