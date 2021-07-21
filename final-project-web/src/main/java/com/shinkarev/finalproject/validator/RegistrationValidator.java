package com.shinkarev.finalproject.validator;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.ParamName.PASSWORD_ERROR;
import static com.shinkarev.finalproject.validator.UserValidator.*;
import static com.shinkarev.finalproject.validator.UserValidator.PASSWORD;

public class RegistrationValidator {
    public static Map<String, String> checkValues(Map<String, String> values, String locale) {
        Map<String, String> result = new HashMap<>();

        UserServiceImpl userService = new UserServiceImpl();
        boolean isError = false;
        String login = values.get(LOGIN.getFieldName());
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

        /*
         *This block checking password on RegEx conformity and checking equality of two entered passwords
         */
        String password = values.get(PASSWORD.getFieldName());
        String checkPassword = values.get(CHECKPASSWORD.getFieldName());
        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (password.equals(checkPassword)) {
                result.put(PASSWORD.getFieldName(), password);
            } else {
                result.put(PASSWORD_ERROR,LocaleSetter.getInstance().getMassage(CHECKPASSWORD.getMessage(), locale));
                isError = true;
            }
        } else {
            result.put(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(PASSWORD.getMessage(), locale));
            isError = true;
        }

        /*
         *This block checking email on RegEx conformity and unique
         */
        String email = values.get(EMAIL.getFieldName());
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (userService.isEmailUnique(email)) {
                result.put(EMAIL.getFieldName(), email);
            } else {
                result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(MESSAGE_ERROR_EMAIL, locale));
                isError = true;
            }
        } else {
            result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(EMAIL.getMessage(), locale));
            isError = true;
        }

        /*
         *This block checking nickname on RegEx conformity
         */
        String nickname = values.get(NICKNAME.getFieldName());
        if (nickname != null && nickname.matches(NICKNAME.getRegExp())) {
            result.put(NICKNAME.getFieldName(), nickname);
        } else {
            result.put(NICKNAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
        }

        /*
         *This block checking name availability
         */
        String name = values.get(NAME.getFieldName());
        if (name != null) {
            result.put(NAME.getFieldName(), name);
        }

        /*
         *This block checking surename availability
         */
        String surename = values.get(SURENAME.getFieldName());
        if (name != null) {
            result.put(SURENAME.getFieldName(), name);
        }

        if (isError) {
            result.clear();
        }
        return result;
    }
}
