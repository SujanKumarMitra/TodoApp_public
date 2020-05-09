package com.herokuapp.skmtodoapp.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	public void sendMail(String toAddress, String subject, String message) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(toAddress);
			mailMessage.setSubject(subject);
			mailMessage.setText(message);
			javaMailSender.send(mailMessage);
			logger.info("Email successfully sent to: "+toAddress);
		} catch (Exception e) {
			logger.severe("Error sending email to: "+toAddress);
			return;
		}
	}


}
