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
    private static final String USER_KEY = "mail.user_name";
    private static final String PASSWORD_KEY = "mail.password";
    private static final String EMAIL_PROPERTIES = "mail.properties";
    private static final String EMAIL_CONFIRMATION = "Email Confirmation";
    private static final String CONTENT_TYPE = "text/html";
    private static EmailService instance;


    private EmailServiceImpl() {
    }

    public static EmailService getInstance() {
        if (instance == null) {
            instance = new EmailServiceImpl();
        }
        return instance;
    }


    @Override
    public boolean sendEmail(String emailTo, String clientMessage) throws ServiceException {
        ResourceManager resourceManager = new ResourceManager();
        Properties properties = resourceManager.getValue(EMAIL_PROPERTIES);
        final String user = properties != null ? properties.getProperty(USER_KEY) : null;
        final String password = properties != null ? properties.getProperty(PASSWORD_KEY) : null;
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
                message.setContent(clientMessage, CONTENT_TYPE);
                Transport.send(message);
                isSent = true;
            }
            return isSent;
        } catch (MessagingException ex) {
            logger.log(Level.ERROR, "Impossible sent email", ex);
            throw new ServiceException("Impossible sent email", ex);
        }
    }
}
