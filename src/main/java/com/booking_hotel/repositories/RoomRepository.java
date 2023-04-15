package com.booking_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking_hotel.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
 
}
