package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.*;
import static com.shinkarev.finalproject.validator.UserValidator.SURENAME;

/**
 * Edit profile validator validates the data entered by the user to edit profile
 *
 * @see InputDataValidator
 */

public class EditProfileValidatorImpl implements InputDataValidator {
    private static InputDataValidator instance;

    private EditProfileValidatorImpl() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new EditProfileValidatorImpl();
        }
        return instance;
    }

    /**
     * @param values Map of edited values where K is field name
     *               and V is - filed value.
     * @param locale - current locale
     * @return Map of errors (if errors present) or empty Map (if not).
     */


    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) {
        Map<String, String> result = new HashMap<>();

        String nickname = values.get(NICKNAME.getFieldName());
        if (nickname == null || !nickname.matches(NICKNAME.getRegExp())) {
            result.put(NICKNAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
        }

        /*
         *This block checking name on RegEx conformity
         */
        String name = values.get(NAME.getFieldName());
        if (name != null) {
            if (!name.matches(NAME.getRegExp())) {
                result.put(NAME_ERROR, LocaleSetter.getInstance().getMassage(NICKNAME.getMessage(), locale));
            }
        }

        /*
         *This block checking surename on RegEx conformity
         */
        String surename = values.get(SURENAME.getFieldName());
        if (surename != null) {
            if (!surename.matches(SURENAME.getRegExp())) {
                result.put(SURENAME_ERROR, LocaleSetter.getInstance().getMassage(SURENAME.getMessage(), locale));
            }
        }
        return result;
    }
}
