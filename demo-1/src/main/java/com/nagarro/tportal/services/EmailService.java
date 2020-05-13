package com.nagarro.tportal.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Email service to send email
 * 
 * @author surbhiagarwal
 *
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	/**
	 * this method sends email tp specified user
	 * 
	 * @param text
	 * @param subject
	 * @param userEmail
	 * @throws MessagingException
	 */
	public void sendEmail(String text, String subject, String userEmail) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(userEmail);
		helper.setText(text, true);
		helper.setSubject(subject);
		try {
			sender.send(message);
		} catch (Exception e) {
			System.out.println("An error occured!");
		}
		System.out.println("Email sent successfully");
	}

}
