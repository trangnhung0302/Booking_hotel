package com.booking_hotel.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index(Authentication authentication) {
    if (authentication == null) {
      return "index";
    }
    List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
    if (authorities.get(0).getAuthority().equals("ADMIN")) {
      return "redirect:admin/reservations";
    }
    else {
      return "index";
    }
  }

  @GetMapping("login_success")
  public String checkRole(Authentication authentication) {
    List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
    if (authorities.get(0).getAuthority().equals("USER")) {
      return "redirect:booking";
    }
    else {
      return "redirect:admin/reservations";
    }
  }
}
