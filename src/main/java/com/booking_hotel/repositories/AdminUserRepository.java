package com.booking_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking_hotel.model.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
  @Query("SELECT a FROM AdminUser a WHERE a.email = ?1")
  public AdminUser findByEmail(String email);
}
