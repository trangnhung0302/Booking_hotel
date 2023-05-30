package com.booking_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking_hotel.model.RoomCategory;

public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Integer> {
  @Query("SELECT rc FROM RoomCategory rc WHERE rc.status = 10")
  public List<RoomCategory> enable();

  public List<RoomCategory> findByNameContaining(String name);

  public List<RoomCategory> findByNameContainingAndMaxNumberOfPeople(String name, int maxNumberOfPeople);

  public List<RoomCategory> findByNameContainingAndStatus(String name, int status);

  public List<RoomCategory> findByNameContainingAndMaxNumberOfPeopleAndStatus(String name, int maxNumberOfPeople,
      int status);
}
