package au.com.phonerent.jma;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.*;
import javax.ejb.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * Email client to handle the smtp email sending
 * @author mac
 */
@Stateless
public class EmailClient {
    private String host = "in-v3.mailjet.com";
    private String from = "phonerent@outlook.com";
    private final String username = "5516087c870da43d5e1716d8ab1483eb";
    private final String password = "417ad7646c8ab221f419edda6cafd2fd";
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
    
    /**
     * sent out the password recovery email
     * @param to email destination
     * @param resetId the password reset id
     * @return whether email sent successed or not
     */
    public boolean passwordRecoverySendTo(String to, String resetId) {
        Message message = getMessageInstance();
        
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your Password Recovery for PhoneRent");
            message.setContent("<h1>You've requested a password recovery for your Phonerent account</h1>"
                    + "<br/>"
                    + "<a href='http://localhost:8080/phonerent-war/faces/password_reset.xhtml?resetId=" + resetId 
                    + "'>Reset Password</a>", "text/html");
            
            Transport.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * sent out the registeration confirmation email
     * @param to email destination
     * @return whether email sent successed or not
     */
    public boolean registerationConfirmationSendTo(String to) {
        Message message = getMessageInstance();
        
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Confrim your registration with Phonerent");
            message.setContent("<h1>You've just signed up with Phonerent</h1>"
                    + "<a href='http://localhost:8080/phonerent-war/faces/confirm_registration.xhtml?email=" + to 
                    + "'>Confirm</a>", "text/html");
            
            Transport.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * get a new message instance
     * @return a new message instance
     */
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
