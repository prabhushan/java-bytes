package com.prabhu;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.prabhu.entity.Reservation;
import com.prabhu.repository.ReservationRepositoy;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ReservationRepositoy rr) {
	    return args -> Stream.of(
	        "Julia", "Mia", "Phil", "Dave", "Pieter",
	        "Bridget", "Stéphane", "Josh", "Jennifer")
	      .forEach(n -> rr.save(new Reservation(n)));
	    }

}
