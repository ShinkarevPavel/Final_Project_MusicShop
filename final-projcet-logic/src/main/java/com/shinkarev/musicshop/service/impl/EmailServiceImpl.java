package com.shinkarev.musicshop.service.impl;

import jakarta.mail.*;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.pool.ResourceManager;
import com.shinkarev.musicshop.service.EmailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.ls.LSOutput;

import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger();
    private final static String USER_KEY = "mail.user_name";
    public static final String PASSWORD_KEY = "mail.password";
    public static final String EMAIL_PROPERTIES = "mail.properties";

    @Override
    public boolean sendEmail(String emailTo, String address) throws ServiceException {
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
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            final String subject = "Email Confirmation";
            message.setSubject(subject);
            final String content = "Click to confirm email: <a href=http://localhost:8080/final_project_web_war_exploded/pages/login.jsp>link</a>";
            final String contentType = "text/html";
            message.setContent(content, contentType);
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            throw new ServiceException("Impossible sent email", ex);
        }
    }
}
