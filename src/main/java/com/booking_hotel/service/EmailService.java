package com.booking_hotel.service;

import com.booking_hotel.entity.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
