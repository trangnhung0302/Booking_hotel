package com.booking_hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking_hotel.model.Customer;
import com.booking_hotel.repositories.CustomerRepository;
import com.booking_hotel.validator.Validator;

@Controller
public class RegisterController {

	@Autowired
	private CustomerRepository customerRepo;

	@GetMapping("/register")
	public String index() {
		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirAttrs)
			throws ServletException, IOException, ParseException {

		Hashtable<String, String> errors = this.validateParams(request);
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			this.saveParams(model, request);
			return "register";
		}
		Customer customer = new Customer();
		customer.setName(request.getParameter("name"));
		customer.setEmail(request.getParameter("email"));
		customer.setTel(request.getParameter("tel"));
		customer.setPassword(request.getParameter("password"));

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);

		model.addAttribute("customer", customer);

		customerRepo.save(customer);
		redirAttrs.addFlashAttribute("message", "Đăng ký thành công");
		return "redirect:login";
	}

	private Hashtable<String, String> validateParams(HttpServletRequest request) throws ParseException {
		Hashtable<String, String> errors = new Hashtable<String, String>();
		Validator validator = new Validator();

		errors = validator.checkRequire("name", request.getParameter("name"), "Tên", errors);
		errors = validator.checkMaxLength("name", request.getParameter("name"), 255, "Tên", errors);

		errors = validator.checkRequire("email", request.getParameter("email"), "Email", errors);
		errors = validator.checkMaxLength("email", request.getParameter("email"), 255, "Email", errors);
		errors = validator.checkEmail("email", request.getParameter("email"), errors);
		errors = this.checkAlreadyEmail("email", request.getParameter("email"), errors);

		errors = validator.checkPhone("tel", request.getParameter("tel"), errors);

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
		return errors;
	}

	private void saveParams(Model model, HttpServletRequest request) {
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("tel", request.getParameter("tel"));
		model.addAttribute("password", request.getParameter("password"));
	}

	public Hashtable<String, String> checkAlreadyEmail(String attrKey, String email, Hashtable<String, String> errors) {
		String message = "Email đã tồn tại!";
		if (customerRepo.findByEmail(email) != null) {
			errors.put(attrKey, message);
		}
		return errors;
	}
}
