package com.drfirst.email.emailTest;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.smtp.SMTPTransport;

public class Email {
	public static void main(String[] args) {
		if (args != null && args.length == 7) {
			EmailVO email = new EmailVO(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			new Email().sendEmail(email);

		}
		else{
			System.out.println("Invalid Arguments");
		}
	}

	private void sendEmail(EmailVO emailVO) {
		Properties props = System.getProperties();
		props.put("mail.smtps.host", emailVO.smtphost);
		props.put("mail.smtps.auth", "true");
		try {
			Session session = Session.getInstance(props);
			SMTPTransport t = null;
			MimeMessage message = buildMessage(session, emailVO, props);
			t = (SMTPTransport) session.getTransport("smtps");
			t.connect(emailVO.smtphost,Integer.parseInt(emailVO.port), emailVO.username, emailVO.password);
			t.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private MimeMessage buildMessage(Session session, EmailVO emailVo, Properties props)
			throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		if ((emailVo.fromEmail != null)) {
			message.setFrom(new InternetAddress(emailVo.fromEmail));
		}
		if ((emailVo.fromEmail != null)) {

			Address[] replyToAddress = { new InternetAddress(emailVo.fromEmail) };
			message.setReplyTo(replyToAddress);
		}
		message.setRecipient(RecipientType.TO, new InternetAddress(emailVo.toEmail));

		message.setSubject("test email");

		message.setContent(emailVo.body, "text/html");
		message.setSentDate(new Date());
		return message;
	}

}

class EmailVO {
	String smtphost;
	String port;
	String username;
	String password;
	String fromEmail;
	String toEmail;
	String body;

	public EmailVO(String smtphost, String port, String username, String password, String fromEmail, String toEmail,
			String body) {
		this.smtphost = smtphost;
		this.port = port;
		this.username = username;
		this.password = password;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.body = body;
	}

}
