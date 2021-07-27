package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import com.shinkarev.musicshop.util.PasswordHashGenerator;

public class RegistrationConfirmator {

    public static String setRegistrationToken(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);
        EmailServiceImpl emailService = new EmailServiceImpl();
        emailService.sendEmail(email, registrationKey);
        return registrationKey;
    }
}
