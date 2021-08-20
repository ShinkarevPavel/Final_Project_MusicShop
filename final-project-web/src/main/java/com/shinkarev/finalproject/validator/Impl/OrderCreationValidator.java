package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.validator.OrderValidator.*;

/**
 * Order creation validator validates the data entered by the user to create order
 *
 * @see InputDataValidator
 */

public class OrderCreationValidator implements InputDataValidator {
    public static InputDataValidator instance;

    private OrderCreationValidator() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new OrderCreationValidator();
        }
        return instance;
    }


    /**
     * @param values Map of registration values where K is field name
     *               and V is - filed value.
     * @param locale - current locale
     * @return Map of errors (if errors present) or empty Map (if not).
     */
    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException {
        Map<String, String> result = new HashMap<>();

        String address = values.get(ADDRESS.getFieldName());
        if (!address.matches(ADDRESS.getRegEx())) {
            result.put(ParamName.ADDRESS_ERROR, LocaleSetter.getInstance().getMassage(ADDRESS.getMessage(), locale));
        }
        return result;
    }
}
