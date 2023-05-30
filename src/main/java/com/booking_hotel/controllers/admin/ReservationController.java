package com.booking_hotel.controllers.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.booking_hotel.controllers.ApplicationController;
import com.booking_hotel.model.Reservation;
import com.booking_hotel.repositories.ReservationRepository;

@Controller
public class ReservationController extends ApplicationController {

  @Autowired
  private ReservationRepository reservationRepository;

  @GetMapping("admin/reservations")
  public String index(Model model, Authentication authentication, HttpServletRequest request) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    String name = "";
    String fromDate = "";
    String toDate = "";
    if (request.getParameter("name") != null) {
      name = request.getParameter("name");
    }
    if (request.getParameter("fromDate") != null) {
      fromDate = request.getParameter("fromDate");
    } else {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm");
      LocalDateTime now = LocalDateTime.now();
      fromDate = dtf.format(now);
    }
    if (request.getParameter("toDate") != null) {
      toDate = request.getParameter("toDate");
    } else {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm");
      LocalDateTime now = LocalDateTime.now().plusDays(30);
      toDate = dtf.format(now);
    }
    model.addAttribute("name", name);
    model.addAttribute("fromDate", fromDate);
    model.addAttribute("toDate", toDate);
    List<Reservation> reservations = reservationRepository.findReservations(name, fromDate, toDate);
    model.addAttribute("reservations", reservations);
    return "admin/reservations/index";
  }
}
