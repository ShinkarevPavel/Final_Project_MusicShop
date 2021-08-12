package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.NICKNAME_ERROR;
import static com.shinkarev.finalproject.validator.UserValidator.NICKNAME;

public class EditProfileValidatorImpl implements InputDataValidator {


    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) {
        Map<String, String> result = new HashMap<>();
        
        String nickname = values.get(NICKNAME.getFieldName());
        if (nickname == null || !nickname.matches(NICKNAME.getRegExp())) {
            result.put(NICKNAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
        }
        return result;

    }
}
