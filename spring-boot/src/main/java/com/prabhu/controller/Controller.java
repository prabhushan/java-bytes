package com.prabhu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prabhu.entity.Reservation;
import com.prabhu.repository.ReservationRepositoy;

@RestController
public class Controller {
	
	@Autowired
	ReservationRepositoy repository;
	
	@RequestMapping(path="/helloworld")
	public List<Reservation> getVersion(){
		return repository.findAll();
	}
	
	@RequestMapping(path="/redirect")
	public void redirectUser(HttpServletResponse response) throws IOException{
		
		response.sendRedirect("http://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect");
		
	}
	
	@RequestMapping(path="/status",method = {
			RequestMethod.GET })
	public String getQueryStatus(@RequestParam(value="filePath", required=false) String a ){
		return a;
	}

}
