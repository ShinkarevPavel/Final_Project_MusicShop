package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import com.shinkarev.musicshop.util.PasswordHashGenerator;

public class RegistrationConfirmator {
    public static final String LINK_HEAD = "Click to confirm email:" +
            " <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=registration_confirmation_command&confirm=";
    public static final String LINK_FOOT = ">link</a>";

    public static String setRegistrationToken(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);
        String link = LINK_HEAD + registrationKey + LINK_FOOT;
        EmailServiceImpl.getInstance().sendEmail(email, link);
        return registrationKey;
    }
}
