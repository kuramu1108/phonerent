/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.jma;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.*;
import javax.ejb.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author mac
 */
@Stateless
public class EmailClient {
    private String host = "express-relay.jangosmtp.net";
    private String from = "info@phonerent.com.au";
    private final String username = "kuramu1108";
    private final String password = "PhoneRent!";
    private Properties props;
    
    public EmailClient() {
        init();
    }
    
//    @PostConstruct
    public void init() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 2525);
    }
    
    public boolean sentTo(String to) {
        Message message = getMessageInstance();
        
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your Password Recovery for PhoneRent");
            message.setContent("<h1>this is testing</h1>", "text/html");
            
            Transport.send(message);
            System.out.println("Message Sent");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    private Message getMessageInstance() {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                    }
                });
        return new MimeMessage(session);
    }
}
