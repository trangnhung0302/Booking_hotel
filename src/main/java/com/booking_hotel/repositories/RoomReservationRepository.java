package com.booking_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking_hotel.model.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {
  
}
