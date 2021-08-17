package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.EmailService;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.util.PasswordHashGenerator;
import com.shinkarev.musicshop.entity.User;

/**
 * Registration confirmator used for registration confirmation.
 */

public class RegistrationConfirmator {
    public static final String LINK_HEAD = "Click to confirm email:" +
            " <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=registration_confirmation_command&confirm=";
    public static final String LINK_FOOT = ">link</a>";

    /**
     *
     * @param email - {@link User}s email specified during registration
     * @param key - certain parameter that was specified by {@link User} during registration that will be code
     *            and sent on user's email with link
     * @return - registration key that will be put to data base
     */
    public static String setRegistrationToken(String email, String key) throws ServiceException {
        String registrationKey = PasswordHashGenerator.encodePassword(key);
        String link = LINK_HEAD + registrationKey + LINK_FOOT;
        EmailService emailService = ServiceProvider.EMAIL_SERVICE;
        emailService.sendEmail(email, link);
        return registrationKey;
    }
}
