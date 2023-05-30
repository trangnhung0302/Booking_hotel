package com.booking_hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String index() {
    return "login";
  }

  @GetMapping("/login_false")
  public String loginFalse(Model model) {
    model.addAttribute("error", "Đăng nhập thất bại. Vui lòng kiểm tra lại!");
    return "login";
  }
}
