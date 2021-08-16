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


public class AdminRegistrationValidatorImpl implements InputDataValidator {
    public static InputDataValidator instance;

    private AdminRegistrationValidatorImpl() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new AdminRegistrationValidatorImpl();
        }
        return instance;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException {
        Map<String, String> result = new HashMap<>();
        UserService userService = ServiceProvider.USER_SERVICE;

        /*
         *This block checking login on RegEx conformity and unique
         */
        String login = values.get(LOGIN.getFieldName());
        if (login != null) {
            if (!userService.isLoginUnique(login)) {
                result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_LOGIN, locale));
            }
        } else {
            result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(LOGIN.getMessage(), locale));
        }
        /*
         *This block checking email on RegEx conformity and unique
         */
        String email = values.get(EMAIL.getFieldName());
        if (email != null) {
            if (!userService.isEmailUnique(email)) {
                result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_EMAIL, locale));
            }
        } else {
            result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(EMAIL.getMessage(), locale));
        }
        return result;
    }
}
