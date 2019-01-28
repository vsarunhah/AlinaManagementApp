import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
 
public class emailSender {
	public emailSender() {
		System.out.println("email could not be sent; no arguments given");
	}
	public emailSender(String toMail, String subject, String mainText) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("varunshah660@gmail.com","vrocker123");
				}
			});
		try {

			Message emailToBeSent = new MimeMessage(session);
			emailToBeSent.setFrom(new InternetAddress("varunshah660@gmail.com"));
			emailToBeSent.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			emailToBeSent.setSubject(subject);
			emailToBeSent.setText(mainText);

			Transport.send(emailToBeSent);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
} 
