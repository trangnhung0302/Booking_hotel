package com.booking_hotel.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class ApplicationController {

  public Boolean isCustomer(Authentication authentication) {
    List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
    if (authorities.get(0).getAuthority().equals("USER")) {
      return true;
    }
    return false;
  }
  public Boolean isAdmin(Authentication authentication) {
    List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
    if (authorities.get(0).getAuthority().equals("ADMIN")) {
      return true;
    }
    return false;
  }
}
