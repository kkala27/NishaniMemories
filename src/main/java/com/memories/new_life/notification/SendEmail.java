package com.memories.new_life.notification;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.memories.new_life.utils.EmailUtil;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendEmail {

	@Value("${SENDGRID_API_KEY}")
	String sendgridApiKey;

	public String sendEmail(String toEmailAddress, String subject, String body) throws IOException {
		log.info(
				"Sending email to => " + toEmailAddress + "\n Email Subject => " + subject + "\n Email Body =>" + body);
		String result = "success";
		Email from = new Email("nishanimemories@gmail.com");
		Email to = new Email(toEmailAddress);
		Content content = new Content("text/html", body);
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(sendgridApiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = (Response) sg.api(request);
			System.out.println(response.getStatusCode());
			return response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "failure";
		}
		return result;
	}

	public String sendEmailSmptp(String toEmailAddress) {
		String result = "success";
		final String fromEmail = "nishani@mail.com"; // requires valid gmail id
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.sendgrid.net"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("apikey",
						"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");
			}
		};
		Session session = Session.getInstance(props, auth);

		EmailUtil.sendEmail(session, toEmailAddress, "TLSEmail Testing Subject", "TLSEmail Testing Body");

		return result;
	}

}
