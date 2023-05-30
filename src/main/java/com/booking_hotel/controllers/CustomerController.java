package com.booking_hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.booking_hotel.model.Customer;
import com.booking_hotel.repositories.CustomerRepository;
import com.booking_hotel.validator.Validator;

@Controller
public class CustomerController extends ApplicationController {

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping("/profile")
  public String show(Model model, Authentication authenticationCheck) {
    if (!this.isCustomer(authenticationCheck)) {
			return "notFound";
		}
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String customerEmail = authentication.getName();
    Customer customer = customerRepository.findByEmail(customerEmail);
    model.addAttribute("customer", customer);
    return "/customer/show";
  }

  @PostMapping("/profile")
  public String update(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirAttrs)
      throws ServletException, IOException, ParseException {

    Hashtable<String, String> errors = this.validateParams(request);
    Customer customer = customerRepository.findByEmail(request.getParameter("email"));
    customer.setName(request.getParameter("name"));
    customer.setTel(request.getParameter("tel"));

    if (!request.getParameter("password").isBlank() || !request.getParameter("rePassword").isBlank()) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(request.getParameter("password"));
      customer.setPassword(encodedPassword);
    }
    if (errors.size() > 0) {
      model.addAttribute("customer", customer);
      model.addAttribute("errors", errors);
      return "/customer/show";
    }

    customerRepository.save(customer);
    redirAttrs.addFlashAttribute("message", "Chỉnh sửa thành công");
    return "redirect:profile";
  }

  private Hashtable<String, String> validateParams(HttpServletRequest request) throws ParseException {
    Hashtable<String, String> errors = new Hashtable<String, String>();
    Validator validator = new Validator();

    errors = validator.checkRequire("name", request.getParameter("name"), "Tên", errors);
    errors = validator.checkMaxLength("name", request.getParameter("name"), 255, "Tên", errors);

    errors = validator.checkPhone("tel", request.getParameter("tel"), errors);

    if (!request.getParameter("password").isBlank() || !request.getParameter("rePassword").isBlank()) {
      errors = validator.checkRequire("password", request.getParameter("password"), "Mật khâu", errors);
      errors = validator.checkMaxLength("password", request.getParameter("password"), 255, "Mật khẩu", errors);
      errors = validator.checkMinLength("password", request.getParameter("password"), 6, "Mật khẩu", errors);

      errors = validator.checkRequire("rePassword", request.getParameter("rePassword"), "Mật khâu xác nhận", errors);
      errors = validator.checkMaxLength("rePassword", request.getParameter("rePassword"), 255, "Mật khẩu xác nhận",
          errors);
      errors = validator.checkMinLength("rePassword", request.getParameter("rePassword"), 6, "Mật khẩu xác nhận",
          errors);

      errors = validator.checkUniformityPassword("rePassword", request.getParameter("password"),
          request.getParameter("rePassword"), errors);
    }

    return errors;
  }
}
