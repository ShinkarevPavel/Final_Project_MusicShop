package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.EmailService;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.util.PasswordHashGenerator;
/**
 * Forgot password confirmator used for creating new password
 * if it was forgotten by {@link User}
 */

public class ForgotPasswordConfirmator {

    public static final String LINK_HEAD = "Click for enter new password:" +
            " <a href=http://localhost:8080/final_project_web_war_exploded/controller?command=to_restore_password_command&confirm=";
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
