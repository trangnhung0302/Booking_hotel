package com.booking_hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.booking_hotel.entity.EmailDetails;
import com.booking_hotel.model.RoomCategory;
import com.booking_hotel.model.Service;
import com.booking_hotel.repositories.RoomCategoryRepository;
import com.booking_hotel.repositories.ServiceRepository;
import com.booking_hotel.service.EmailService;

import java.util.List;

@Controller
public class BookingController {
	
	@Autowired private EmailService emailService;

    @Autowired private RoomCategoryRepository roomCategoryRepository;
    
    @Autowired private ServiceRepository serviceRepository;
	
	@GetMapping("/booking")
    public String index(Model model) {
        List<RoomCategory> roomCategories = roomCategoryRepository.enable();
        List<Service> services = serviceRepository.enable();
        model.addAttribute("roomCategories", roomCategories);
        model.addAttribute("services", services);
        return "booking";
    }
	
	@GetMapping("/sendMail")
    public String sendMail() throws InterruptedException
    {
		for (int i = 0; i < 100; i++) {			
			EmailDetails details = new EmailDetails();
			details.setRecipient("nhungmt32@gmail.com");
			details.setMsgBody("Tao là Nhung Cún");
			details.setSubject("Gâu Gâu Gâu");
			emailService.sendSimpleMail(details);
			System.out.println("Send " + i);
			Thread.sleep(60000);
		}
		return "done";
    }
 
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
        @RequestBody EmailDetails details)
    {
        String status
            = emailService.sendMailWithAttachment(details);
 
        return status;
    }
}
