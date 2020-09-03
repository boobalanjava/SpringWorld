package com.springworld.ses.sender;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class SESMailSender {

    
	public void sendMailSES() throws UnsupportedEncodingException, MessagingException { 
		String FROM = "fromemail@mail.com";
	    String FROMNAME = "AWS_SES";
	    String TO = "toemail@mail.com";
	    String SMTP_USERNAME = "YOUR_SMTP_UN";
	    String SMTP_PASSWORD = "YOUR_SMTP_PASSWORD";
	    String HOST = "YOUR_SMTP_HOST";
	    int PORT = 587;
	    
	    String SUBJECT = "Amazon SES test";
	    
	     String BODY = String.join(
	    	    System.getProperty("line.separator"),
	    	    "<h1>Amazon SES SMTP Email Test</h1>",
	    	    "<p>This email was sent with Amazon SES using the ", 
	    	    "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
	    	    " for <a href='https://www.java.com'>Java</a>."
	    	);

	    

	        // Create a Properties object to contain connection configuration information.
	    	Properties props = System.getProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", PORT); 
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.smtp.auth", "true");

	        // Create a Session object to represent a mail session with the specified properties. 
	    	Session session = Session.getDefaultInstance(props);

	        // Create a message with the specified information. 
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(FROM,FROMNAME));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	        msg.setSubject(SUBJECT);
	        msg.setContent(BODY,"text/html");
	        
	        Transport transport = session.getTransport();
	                    
	        // Send the message.
	        try
	        {
	            System.out.println("Sending...");
	            
	            // Connect to Amazon SES using the SMTP username and password you specified above.
	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
	        	
	            // Send the email.
	            transport.sendMessage(msg, msg.getAllRecipients());
	            System.out.println("Email sent!");
	        }
	        catch (Exception ex) {
	            System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            transport.close();
	        }
	    
	}
    
}