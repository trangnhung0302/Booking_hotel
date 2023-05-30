package com.booking_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking_hotel.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
  public List<Reservation> findReservationByRoomsId(Integer roomId);

  @Query(value = "Select * From reservations r " +
      "join room_reservations rr ON r.id = rr.reservation_id " +
      "Where ((r.start_time BETWEEN ?1 AND ?2) OR (r.end_time BETWEEN ?1 AND ?2)) " +
      "AND r.cancel_time IS NULL " +
      "AND rr.room_id = ?3", nativeQuery = true)
  public List<Reservation> checkReserves(String startTime, String endTime, Integer roomId);

  public List<Reservation> findByCustomerId(Integer customerId);

  @Query(value = "Select * From reservations r " +
      "Join customers c ON c.id = r.customer_id " +
      "Where c.name LIKE %?1% " +
      "AND r.start_time >= ?2 " +
      "AND r.end_time <= ?3", nativeQuery = true)
  public List<Reservation> findReservations(String name, String fromDate, String toDate);
}
