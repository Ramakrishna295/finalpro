package com.nkxgen.spring.jdbc.validation;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailSenderImpl implements MailSender {
	public String send(String to_user) {
		String to = to_user;
		String otp = generateOTP();
		String subject = "Password Reset";
		String body = "The OTP for the Password Reset: " + otp;
		MailSenderImpl m = new MailSenderImpl();
		m.sendEmail(to, subject, body);
		return otp;
	}

	private static void sendEmail(String to, String subject, String body) {

		String host = "smtp.gmail.com";
		int port = 587;

		final String username = "pametineeraj2001@gmail.com";
		final String password = "agvxucccuiyrehqv";

		// Set properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Create session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			// Send message
			Transport.send(message);

			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static String generateOTP() {
		Random random = new Random();
		String s = "";
		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt(10);
			s = s + String.valueOf(randomIndex);

		}

		return s;
	}

}
