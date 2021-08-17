package com.shinkarev.finalproject.validator;

import com.shinkarev.musicshop.exception.ServiceException;

import java.util.Map;

public interface InputDataValidator {

    /**
     * @param values Map of input values where K is field name
     *               and V is - filed value.
     * @param locale - current locale
     * @return Map of errors (if errors present) or empty Map (if not)
     */
    Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException;
}
