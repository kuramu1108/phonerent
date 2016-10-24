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
    private String host = "in-v3.mailjet.com";
    private String from = "colagarychen@gmail.com";
    private final String username = "5516087c870da43d5e1716d8ab1483eb";
    private final String password = "ca112c066d6d36a475c105f6a9c27037";
    private Properties props;
    
    public EmailClient() {
        
    }
    
    @PostConstruct
    public void init() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
    }
    
    public boolean passwordRecoverySendTo(String to, String resetId) {
        Message message = getMessageInstance();
        
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your Password Recovery for PhoneRent");
            message.setContent("<h1>this is testing</h1>"
                    + "<br/>"
                    + "<a href='http://localhost:8080/phonerent-war/faces/password_reset.xhtml?resetId=" + resetId 
                    + "'>Reset Password</>", "text/html");
            
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
