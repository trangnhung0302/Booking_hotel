package com.booking_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking_hotel.model.ServiceReservation;

public interface ServiceReservationRepository extends JpaRepository<ServiceReservation, Integer> {
  
}
