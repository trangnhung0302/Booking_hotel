package com.booking_hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne 
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Column(name = "start_time")
  private String startTime;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  @Column(name = "end_time")
  private String endTime;

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Column(name = "cancel_time")
  private String cancelTime;

  public String getCancelTime() {
    return cancelTime;
  }

  public void setCancelTime(String cancelTime) {
    this.cancelTime = cancelTime;
  }

  @Column(name = "number_of_adults")
  private Integer numberOfAdults;

  public Integer getNumberOfAdults() {
    return numberOfAdults;
  }

  public void setNumberOfAdults(Integer numberOfAdults) {
    this.numberOfAdults = numberOfAdults;
  }

  @Column(name = "number_of_children")
  private Integer numberOfChildren;

  public Integer getNumberOfChildren() {
    return numberOfChildren;
  }

  public void setNumberOfChildren(Integer numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
  }

  @ManyToMany
  @MapsId("roomId")
  @JoinTable(name = "room_reservations",
          joinColumns = @JoinColumn(name = "reservation_id"),
          inverseJoinColumns = @JoinColumn(name = "room_id"))
  private Set<Room> rooms = new HashSet<>();

  public Set<Room> getRooms() {
    return rooms;
  }

  public void setRooms(Set<Room> rooms) {
    this.rooms = rooms;
  }

  @ManyToMany
  @MapsId("serviceId")
  @JoinTable(name = "service_reservations",
          joinColumns = @JoinColumn(name = "reservation_id"),
          inverseJoinColumns = @JoinColumn(name = "service_id"))
  private Set<Service> services = new HashSet<>();

  public Set<Service> getServices() {
    return services;
  }

  public void setServices(Set<Service> services) {
    this.services = services;
  }
}
