package com.booking_hotel.controllers.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.booking_hotel.model.RoomCategory;
import com.booking_hotel.repositories.RoomCategoryRepository;
import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomCategoryController {
	@Autowired private RoomCategoryRepository repo;
     
  @GetMapping("/api/v1/room_categories")
  public String listAll(Model model) {
    List<RoomCategory> roomCategories = repo.enable();
    Gson gson = new Gson();
    String daysJson = gson.toJson(roomCategories);
    return daysJson;
  }
}
