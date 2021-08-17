package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.UserValidator.EMAIL;

/**
 * Forgot password validator validates the data entered by the user for changing password
 *
 * @see InputDataValidator
 */

public class ForgotPasswordValidator implements InputDataValidator {
    private static InputDataValidator instance;

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new ForgotPasswordValidator();
        }
        return instance;
    }

    /**
     *
     * @param values Map of input values where K is field name
     *               and V is - filed value.
     * @param locale - current locale
     * @return Map of errors (if errors present) or empty Map (if not).
     */

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException {
        Map<String, String> result = new HashMap<>();
        UserService userService = ServiceProvider.USER_SERVICE;

        String email = values.get(EMAIL.getFieldName());
        if (email != null && email.matches(EMAIL.getRegExp())) {
            if (userService.isEmailUnique(email)) {
                result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(PAGE_FORGOT_PASSWORD, locale));
            }
        } else {
            result.put(EMAIL_ERROR, LocaleSetter.getInstance().getMassage(EMAIL.getMessage(), locale));
        }
        return result;
    }
}
