package com.booking_hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking_hotel.model.Customer;
import com.booking_hotel.repositories.CustomerRepository;

@Controller
public class RegisterController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("/register")
    public String index() {
        return "register";
    }
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException {
		Customer customer = new Customer();
		customer.setName(request.getParameter("name"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(customer.getPassword());
	    customer.setPassword(encodedPassword);

		model.addAttribute("customer", customer);
	     
	    customerRepo.save(customer);
	    return "login";
	}
}
