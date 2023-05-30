package com.booking_hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "admin_users")
public class AdminUser {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;

  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
