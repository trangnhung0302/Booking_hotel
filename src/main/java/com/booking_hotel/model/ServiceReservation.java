package com.booking_hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "service_reservations")
public class ServiceReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
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
