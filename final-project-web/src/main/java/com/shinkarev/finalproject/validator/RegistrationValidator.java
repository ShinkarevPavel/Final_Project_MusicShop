package com.shinkarev.finalproject.validator;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.LOGIN_ERROR;
import static com.shinkarev.finalproject.command.ParamName.MESSAGE_ERROR_LOGIN;
import static com.shinkarev.finalproject.validator.UserValidator.LOGIN;

public class RegistrationValidator {
    public static Map<String, String> checkValues(Map<String, String> values, String locale) {
        Map<String, String> result = new HashMap<>();

        UserServiceImpl userService = new UserServiceImpl();
        String login = values.get("login");
        boolean isError = false;
        /*
         *This block checking login on RegEx conformity and unique
         */
        if (login != null && login.matches(LOGIN.getRegExp())) {
            if (userService.isLoginUnique(login)) {
                result.put(LOGIN.getFieldName(), login);
            } else {
                result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_LOGIN, locale));
                isError = true;
            }
        } else {
            result.put(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(LOGIN.getMessage(), locale));
            isError = true;
        }


        if (!isError) {
            result = null;
        }
        return result;
    }
}
