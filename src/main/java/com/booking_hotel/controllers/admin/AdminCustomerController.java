package com.booking_hotel.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.booking_hotel.controllers.ApplicationController;
import com.booking_hotel.model.Customer;
import com.booking_hotel.repositories.CustomerRepository;

@Controller
public class AdminCustomerController extends ApplicationController {

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping("/admin/customers")
  public String index(Model model, Authentication authentication, HttpServletRequest request) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    // model.addAttribute("name", request.getParameter("name"));
    // model.addAttribute("email", request.getParameter("email"));
    // model.addAttribute("tel", request.getParameter("tel"));

    String name = "";
    String email = "";
    String tel = "";

    if (request.getParameter("name") != null) {
      name = request.getParameter("name");
    }
    if (request.getParameter("email") != null) {
      email = request.getParameter("email");
    }
    if (request.getParameter("tel") != null) {
      tel = request.getParameter("tel");
    }

    List<Customer> customers = customerRepository.findByNameContainingAndEmailContainingAndTelContaining(name, email,
        tel);
    model.addAttribute("customers", customers);
    return "/admin/customers/index";
  }
}
