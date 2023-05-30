package com.booking_hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "room_reservations")
public class RoomReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  @ManyToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }
}
