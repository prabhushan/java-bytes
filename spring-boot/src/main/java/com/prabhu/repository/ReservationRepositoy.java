package com.prabhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prabhu.entity.Reservation;

@Repository
public interface ReservationRepositoy extends JpaRepository<Reservation, String>{

	List<Reservation> findAll();
	
	Reservation save(Reservation reservation);
	
}
