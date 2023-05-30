package com.booking_hotel.controllers.admin;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booking_hotel.controllers.ApplicationController;
import com.booking_hotel.model.Room;
import com.booking_hotel.model.RoomCategory;
import com.booking_hotel.repositories.RoomCategoryRepository;
import com.booking_hotel.repositories.RoomRepository;
import com.booking_hotel.validator.Validator;

@Controller
public class RoomController extends ApplicationController {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private RoomCategoryRepository roomCategoryRepository;

  @GetMapping("/admin/room_categories/{id}/rooms")
  public String index(@PathVariable Integer id, Model model, Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    List<Room> rooms = roomRepository.findByRoomCategoryId(id);
    RoomCategory roomCategory = roomCategoryRepository.findById(id).get();
    model.addAttribute("roomCategory", roomCategory);
    model.addAttribute("rooms", rooms);
    return "admin/rooms/index";
  }

  @GetMapping("/admin/room_categories/{id}/rooms/{roomId}")
  public String update(@PathVariable Integer id, @PathVariable Integer roomId, Model model, Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    Room room = roomRepository.findById(roomId).get();
    if (room.getStatus() == 10) {
      room.setStatus(90);
    } else {
      room.setStatus(10);
    }
    roomRepository.save(room);
    return "redirect:../../../../admin/room_categories/" + id + "/rooms";
  }

  @PostMapping("/admin/room_categories/{id}/rooms")
  public String create(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model,
      RedirectAttributes redirAttrs)
      throws ServletException, IOException {
    Hashtable<String, String> errors = this.validateParams(request);
    if (errors.size() > 0) {
      redirAttrs.addFlashAttribute("messageFail", "Tạo phòng thất bại");
      return "redirect:/admin/room_categories/" + id + "/rooms";
    }
    RoomCategory roomCategory = roomCategoryRepository.findById(id).get();
    Room room = new Room();
    room.setRoomCategory(roomCategory);
    room.setName(request.getParameter("name"));
    room.setStatus(10);
    roomRepository.save(room);
    redirAttrs.addFlashAttribute("message", "Tạo phòng thành công");
    return "redirect:/admin/room_categories/" + id + "/rooms";
  }

  private Hashtable<String, String> validateParams(HttpServletRequest request) {
    Hashtable<String, String> errors = new Hashtable<String, String>();
    Validator validator = new Validator();

    errors = validator.checkRequire("name", request.getParameter("name"), "Tên phòng", errors);
    errors = validator.checkMaxLength("name", request.getParameter("name"), 255, "Tên phòng", errors);

    return errors;
  }
}
