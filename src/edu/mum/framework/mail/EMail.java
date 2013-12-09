package edu.mum.framework.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.event.MailEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EMail {

    private static EMail mail = null;
    
    private EMail(){
    }
    
    public static EMail getInstance() {
    	if(mail == null) {
    		mail = new EMail();    		
    	}
    	return mail;
    }
    
    public void sendMail(Mail mail) {
        Properties props;
    	props = new Properties();
        props.put("mail.smtp.host", "aspmx.l.google.com");
        props.put("mail.smtp.port", "25");
        
        Session mailSession = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(mailSession);
 
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
        try {
            fromAddress = new InternetAddress(mail.getFrom());
            toAddress = new InternetAddress(mail.getTo());
        } catch (AddressException e) {
            e.printStackTrace();
        }
 
        try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
            simpleMessage.setSubject(mail.getSubject());
            simpleMessage.setText(mail.getText());
 
            Transport.send(simpleMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
