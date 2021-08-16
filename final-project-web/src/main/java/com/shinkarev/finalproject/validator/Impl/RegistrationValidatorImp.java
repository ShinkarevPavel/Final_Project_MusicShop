package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;


import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;

public class RegistrationValidatorImp implements InputDataValidator {

    private static InputDataValidator instance;

    private RegistrationValidatorImp() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new RegistrationValidatorImp();
        }
        return instance;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException {
        Map<String, String> result = new HashMap<>();
        UserService userService = ServiceProvider.USER_SERVICE;
        String login = values.get(LOGIN.getFieldName());
        /*
         *This block checking login on RegEx conformity and unique
         */
        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (!userService.isLoginUnique(login)) {
                result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_LOGIN, locale));
            }
        } else {
            result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(LOGIN.getMessage(), locale));
        }

        /*
         *This block checking password on RegEx conformity and checking equality of two entered passwords
         */
        String password = values.get(PASSWORD.getFieldName());
        String checkPassword = values.get(CHECKPASSWORD.getFieldName());
        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (!password.equals(checkPassword)) {
                result.put(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(CHECKPASSWORD.getMessage(), locale));
            }
        } else {
            result.put(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(PASSWORD.getMessage(), locale));
        }

        /*
         *This block checking email on RegEx conformity and unique
         */
        String email = values.get(EMAIL.getFieldName());
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (!userService.isEmailUnique(email)) {
                result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_EMAIL, locale));
            }

        } else {
            result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(EMAIL.getMessage(), locale));
        }

        /*
         *This block checking nickname on RegEx conformity
         */
        String nickname = values.get(NICKNAME.getFieldName());
        if (nickname == null || !nickname.matches(NICKNAME.getRegExp())) {
            result.put(NICKNAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
        }
        return result;
    }
}
