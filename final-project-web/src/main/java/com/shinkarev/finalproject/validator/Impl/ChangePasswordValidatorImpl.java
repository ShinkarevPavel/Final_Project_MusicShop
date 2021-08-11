package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.PASSWORD_ERROR;
import static com.shinkarev.finalproject.validator.UserValidator.CHECKPASSWORD;
import static com.shinkarev.finalproject.validator.UserValidator.PASSWORD;

public class ChangePasswordValidatorImpl implements InputDataValidator {

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) {

        Map<String, String> result = new HashMap<>();
        String password = values.get(PASSWORD.getFieldName());
        String checkPassword = values.get(CHECKPASSWORD.getFieldName());

        if (password != null && password.matches(PASSWORD.getRegExp())) {
            if (!password.equals(checkPassword)) {
                result.put(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(CHECKPASSWORD.getMessage(), locale));
            }
        } else {
            result.put(PASSWORD_ERROR, LocaleSetter.getInstance().getMassage(PASSWORD.getMessage(), locale));
        }
        return null;
    }
}
