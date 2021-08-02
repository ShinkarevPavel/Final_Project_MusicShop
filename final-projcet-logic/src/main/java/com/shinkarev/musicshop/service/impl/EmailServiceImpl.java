package com.shinkarev.musicshop.service.impl;

import jakarta.mail.*;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.pool.ResourceManager;
import com.shinkarev.musicshop.service.EmailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger();
    private final static String USER_KEY = "mail.user_name";
    public static final String PASSWORD_KEY = "mail.password";
    public static final String EMAIL_PROPERTIES = "mail.properties";
    public static final String EMAIL_CONFIRMATION = "Email Confirmation";
    public static final String CONTENT_TYPE = "text/html";
    public static final String LINK_HEAD = "Click to confirm email: <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=registration_confirmation_command&confirm=";
    public static final String LINK_FOOT = ">link</a>";
    @Override
    public boolean sendEmail(String emailTo, String key) throws ServiceException {
        final String content = LINK_HEAD + key + LINK_FOOT;
        ResourceManager resourceManager = new ResourceManager();
        Properties properties = resourceManager.getValue(EMAIL_PROPERTIES);
        final String user = properties != null ? properties.getProperty(USER_KEY) : null;
        final String password = properties != null ? properties.getProperty(PASSWORD_KEY) : null;
        System.out.println(user + password);
        final Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        Message message = new MimeMessage(session);
        try {
            boolean isSent = false;
            if (user != null) {
                message.setFrom(new InternetAddress(user));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
                message.setSubject(EMAIL_CONFIRMATION);
                message.setContent(content, CONTENT_TYPE);
                Transport.send(message);
                System.out.println("here");
                isSent = true;
            }
            return isSent;
        } catch (MessagingException ex) {
            logger.log(Level.ERROR, "Impossible sent email", ex);
            throw new ServiceException("Impossible sent email", ex);
        }
    }
}
