package com.booking_hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "room_categories")
public class RoomCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String name;
	
	@Column(name = "max_number_of_people")
	private Integer maxNumberOfPeople;
	
	public Integer getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(Integer maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	@Column(name = "price_of_day")
	private Integer priceOfDay;
	
	public Integer getPriceOfDay() {
		return priceOfDay;
	}

	public void setPriceOfDay(Integer priceOfDay) {
		this.priceOfDay = priceOfDay;
	}

	@Column(name = "price_overnight")
	private Integer priceOvernight;
	
	public Integer getPriceOvernight() {
		return priceOvernight;
	}

	public void setPriceOvernight(Integer priceOvernight) {
		this.priceOvernight = priceOvernight;
	}

	@Column(name = "price_of_hour")
	private Integer priceOfHour;
	
	public Integer getPriceOfHour() {
		return priceOfHour;
	}

	public void setPriceOfHour(Integer priceOfHour) {
		this.priceOfHour = priceOfHour;
	}

	@Column(name = "image_url")
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "status")
	private Integer status;
	
	@Column(name = "remark")
	private String remark;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(mappedBy = "roomCategory")
	private Set<Room> room = new HashSet<>();
}
    
    
   