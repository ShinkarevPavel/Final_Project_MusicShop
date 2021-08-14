package com.shinkarev.finalproject.validator;

import com.shinkarev.musicshop.exception.ServiceException;

import java.util.Map;

public interface InputDataValidator {
    Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException;
}
