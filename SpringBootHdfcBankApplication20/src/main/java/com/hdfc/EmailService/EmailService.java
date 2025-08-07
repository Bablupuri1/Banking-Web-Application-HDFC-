
package com.hdfc.EmailService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendUserCredentials(String toEmail, String Customerid, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("🎉 Welcome to HDFC BANK - Your Account Details");
		message.setSentDate(new Date());
		message.setText("Hi " + Customerid + ",\n\n" + "Your account has been created successfully!\n\n" + "🆔 Username: "
				+ Customerid + "\n" + "🔐 Password: " + password + "\n\n"
				+ "You can now login and start exploring our Services.\n\n" + "Regards,\n" + "HDFC BANK Team");

		// Optional: Uncomment if needed
		// message.setFrom("bablugiri1947@gmail.com");

		mailSender.send(message);
	}
}
