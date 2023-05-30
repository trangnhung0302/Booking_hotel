package com.booking_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking_hotel.model.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
  public List<Room> findByRoomCategoryId(Integer roomCategoryId);

  @Query("Select r From Room r Where r.status = 10")
  public List<Room> enable();
}
