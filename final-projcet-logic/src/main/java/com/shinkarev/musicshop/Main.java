package com.shinkarev.musicshop;

import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.EmailService;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;

public class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailServiceImpl();
        try {
            emailService.sendEmail("pavelshinkarev209@gmail.com", "U a amazing!");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
