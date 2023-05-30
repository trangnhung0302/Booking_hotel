package com.booking_hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booking_hotel.entity.EmailDetails;
import com.booking_hotel.model.Customer;
import com.booking_hotel.model.Reservation;
import com.booking_hotel.model.Room;
import com.booking_hotel.model.RoomCategory;
import com.booking_hotel.model.RoomReservation;
import com.booking_hotel.model.Service;
import com.booking_hotel.model.ServiceReservation;
import com.booking_hotel.repositories.CustomerRepository;
import com.booking_hotel.repositories.ReservationRepository;
import com.booking_hotel.repositories.RoomCategoryRepository;
import com.booking_hotel.repositories.RoomRepository;
import com.booking_hotel.repositories.RoomReservationRepository;
import com.booking_hotel.repositories.ServiceRepository;
import com.booking_hotel.repositories.ServiceReservationRepository;
import com.booking_hotel.service.EmailService;
import com.booking_hotel.validator.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookingController extends ApplicationController{
	
	@Autowired private EmailService emailService;

	@Autowired private RoomCategoryRepository roomCategoryRepository;

	@Autowired private RoomRepository roomRepository;
	
	@Autowired private ServiceRepository serviceRepository;

	@Autowired private ReservationRepository reservationRepository;

	@Autowired private CustomerRepository customerRepository;

	@Autowired private RoomReservationRepository roomReservationRepository;

	@Autowired private ServiceReservationRepository serviceReservationRepository;
	
	@GetMapping("/booking")
	public String index(Model model, Authentication authentication) {
		if (!this.isCustomer(authentication)) {
			return "notFound";
		}
		List<RoomCategory> roomCategories = roomCategoryRepository.enable();
		List<Service> services = serviceRepository.enable();
		model.addAttribute("roomCategories", roomCategories);
		model.addAttribute("services", services);
		return "booking/index";
	}

	@GetMapping("/booking/{id}")
	public String show(@PathVariable Integer id, Model model, Authentication authentication) {
		if (!this.isCustomer(authentication)) {
			return "notFound";
		}
		Reservation reservation = reservationRepository.findById(id).get();
		model.addAttribute("reservation", reservation);
		return "booking/show";
	}

	@GetMapping("/history")
	public String history(Model model, Authentication authenticationCheck) {
		if (!this.isCustomer(authenticationCheck)) {
			return "notFound";
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String customerEmail = authentication.getName();
		Customer customer = customerRepository.findByEmail(customerEmail);
		List<Reservation> reservations = reservationRepository.findByCustomerId(customer.getId());
		model.addAttribute("reservations", reservations);
		return "/booking/history";
	}

	@GetMapping("/booking/cancel/{id}")
	public String cancel(@PathVariable Integer id, RedirectAttributes redirAttrs, Authentication authentication) {
		if (!this.isCustomer(authentication)) {
			return "notFound";
		}
		Reservation reservation = reservationRepository.findById(id).get();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
		reservation.setCancelTime(dtf.format(now));
		reservationRepository.save(reservation);
		redirAttrs.addFlashAttribute("message", "Hủy phòng thành công");
		return "redirect:../../booking/" + id.toString();
	}

	@PostMapping("/booking")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirAttrs)
									throws ServletException, IOException, ParseException {
		Hashtable<String, String> errors = this.validateParams(request);
		this.saveParams(model, request);
		if (errors.size() > 0) {
			return this.returnError(model, errors);
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Integer numberOfAdults = Integer.parseInt(request.getParameter("numberOfAdults"));
		Integer numberOfChildren = Integer.parseInt(request.getParameter("numberOfChildren"));
		Integer roomCategoryId = Integer.parseInt(request.getParameter("roomCategoryId"));
		String[] serviceIds = request.getParameterValues("serviceIds");


		List<Integer> roomIds = this.findRoomIds(roomCategoryId, numberOfAdults + numberOfChildren, startTime, endTime);

		if (roomIds.size() == 0) {
			errors.put("roomNumber", "Xin lỗi đã hết phòng");
			return this.returnError(model, errors);
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String customerEmail = authentication.getName();
		Customer customer = customerRepository.findByEmail(customerEmail);
		Reservation reservation = new Reservation();
		reservation.setCustomer(customer);
		reservation.setStartTime(startTime);
		reservation.setEndTime(endTime);
		reservation.setNumberOfAdults(numberOfAdults);
		reservation.setNumberOfChildren(numberOfChildren);
		reservationRepository.save(reservation);

		for(Integer roomId:roomIds) {
			Room room = roomRepository.findById(roomId).get();
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoom(room);
			roomReservation.setReservation(reservation);
			roomReservationRepository.save(roomReservation);
		}

		if (serviceIds != null) {
			for(String serviceId:serviceIds) {
				Service service = serviceRepository.findById(Integer.parseInt(serviceId)).get();
				ServiceReservation serviceReservation = new ServiceReservation();
				serviceReservation.setService(service);
				serviceReservation.setReservation(reservation);
				serviceReservationRepository.save(serviceReservation);
			}
		}
		this.sendMail(customerEmail);
		List<Reservation> reservations = reservationRepository.findByCustomerId(customer.getId());
		model.addAttribute("reservations", reservations);
		redirAttrs.addFlashAttribute("message", "Đặt phòng thành công");
		return "redirect:history";
	}
	
	private void sendMail(String email)
	{		
		EmailDetails details = new EmailDetails();
		details.setRecipient(email);
		details.setMsgBody("Đặt phòng thành công");
		details.setSubject("Đặt phòng thành công");
		emailService.sendSimpleMail(details);
	}
	
	private List<Integer> findRoomIds(Integer roomCategoryId, Integer numberOfPeople, String startTime, String endTime) {
		List<Integer> roomIds = new ArrayList<Integer>();
		RoomCategory roomCategory = roomCategoryRepository.findById(roomCategoryId).get();
		Integer numberOfRoom = numberOfPeople / roomCategory.getMaxNumberOfPeople();
		if (numberOfPeople % roomCategory.getMaxNumberOfPeople() !=0 ) {
			numberOfRoom++;
		}
		List<Room> rooms = roomRepository.findByRoomCategoryId(roomCategoryId);
		if (rooms.size() < numberOfRoom) return roomIds;
		for(Room room : rooms) {
			List<Reservation> reservations = reservationRepository.checkReserves(startTime, endTime, room.getId());
			if (reservations.size() == 0) {
				roomIds.add(room.getId());
				if (roomIds.size() == numberOfRoom) {
					return roomIds;
				}
			}
		}
		if (roomIds.size() < numberOfRoom) return  new ArrayList<Integer>();
		return roomIds;
	}
	private Hashtable<String, String> validateParams(HttpServletRequest request) throws ParseException {
		Hashtable<String, String> errors = new Hashtable<String, String>();
		Validator validator = new Validator();
		errors = validator.checkRequire("startTime", request.getParameter("startTime"), "ngày nhận phòng", errors);
		errors = validator.checkCurrentTime("startTime", request.getParameter("startTime"), "Ngày nhận phòng", errors);
		errors = validator.checkPeriodTime("startTime", request.getParameter("startTime"), request.getParameter("endTime"), errors);

		errors = validator.checkRequire("endTime", request.getParameter("endTime"), "ngày trả phòng", errors);
		errors = validator.checkCurrentTime("endTime", request.getParameter("endTime"), "Ngày trả phòng", errors);

		errors = validator.checkRequire("numberOfAdults", request.getParameter("numberOfAdults"), "số người lớn", errors);
		errors = validator.checkNumber("numberOfAdults", request.getParameter("numberOfAdults"), "số người lớn", errors);

		errors = validator.checkRequire("numberOfChildren", request.getParameter("numberOfChildren"), "số trẻ con", errors);
		errors = validator.checkNumberHasZezo("numberOfChildren", request.getParameter("numberOfChildren"), "số trẻ con", errors);

		errors = validator.checkRequire("roomCategoryId", request.getParameter("roomCategoryId"), "loại phòng", errors);
		return errors;
	}
	private String returnError(Model model, Hashtable<String, String> errors) {
		model.addAttribute("errors", errors);
		List<RoomCategory> roomCategories = roomCategoryRepository.enable();
		List<Service> services = serviceRepository.enable();
		model.addAttribute("roomCategories", roomCategories);
		model.addAttribute("services", services);
		return "booking/index";
	}
	private Model saveParams(Model model, HttpServletRequest request) {
		model.addAttribute("startTime", request.getParameter("startTime"));
		model.addAttribute("endTime", request.getParameter("endTime"));
		model.addAttribute("numberOfAdults", request.getParameter("numberOfAdults"));
		model.addAttribute("numberOfChildren", request.getParameter("numberOfChildren"));
		model.addAttribute("roomCategoryId", request.getParameter("roomCategoryId"));
		model.addAttribute("serviceIds", request.getParameterValues("serviceIds"));
		return model;
	}
}
