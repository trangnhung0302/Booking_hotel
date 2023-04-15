package com.booking_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking_hotel.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
  @Query("SELECT s FROM Service s WHERE s.status = 10")
  public List<Service> enable();
}
