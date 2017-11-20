package com.javabytes.edifactparser;

import java.util.Map;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javabytes.edifact.version.ParserFactory;

@Controller
public class WebResource {

	@Autowired
	ParserFactory parserFactory;
	
	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "sai ram");
		return "hello";
	}
	
	@GetMapping("/model")
	public Map view(Map<String, Object> model) {
		model.put("message", "sai ram");
		return model;
	}
	
	@PostMapping("/parse")
	public String parse(@RequestParam("request") String request,@RequestParam("type") String type){
		System.out.println(request);
		System.out.println(type);
		System.out.println(parserFactory.getParser(type).parseRequest(request));
		return "hello";
	}
	
}
