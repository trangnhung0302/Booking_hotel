package com.booking_hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "room_categories")
public class RoomCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String name;
	
	private Integer max_of_people;
	
	private Integer price_of_day;
	
	private Integer price_overnight;
	
	private Integer price_of_hour;
	
	private String image_url;
	
	private Integer status;
	
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

	public Integer getMax_of_people() {
		return max_of_people;
	}

	public void setMax_of_people(Integer max_of_people) {
		this.max_of_people = max_of_people;
	}

	public Integer getPrice_of_day() {
		return price_of_day;
	}

	public void setPrice_of_day(Integer price_of_day) {
		this.price_of_day = price_of_day;
	}

	public Integer getPrice_overnight() {
		return price_overnight;
	}

	public void setPrice_overnight(Integer price_overnight) {
		this.price_overnight = price_overnight;
	}

	public Integer getPrice_of_hour() {
		return price_of_hour;
	}

	public void setPrice_of_hour(Integer price_of_hour) {
		this.price_of_hour = price_of_hour;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
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

	
}
    
    
   