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
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class emailSender {
	public static void main(String[] args) {
	 String to = "varunshah660@gmail.com";//change accordingly  
     String from = "varunshah660@gmail.com";//change accordingly  
     String host = "localhost";//or IP address  
 
    //Get the session object  
     Properties properties = System.getProperties();  
     properties.setProperty("mail.smtp.host", host);  
     Session session = Session.getDefaultInstance(properties);  
 
    //compose the message
     try{  
        MimeMessage message = new MimeMessage(session);  
        message.setFrom(new InternetAddress(from));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
        message.setSubject("Ping");  
        message.setText("Hello, this is example of sending email  ");  
 
        // Send message  
        Transport.send(message);  
        System.out.println("message sent successfully....");  
 
     }catch (MessagingException mex) {mex.printStackTrace();}  
  }  
} 
