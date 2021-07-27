package com.shinkarev.finalproject.validator;

import java.util.Map;

public interface InputDataValidator {
    Map<String, String> checkValues(Map<String, String> values, String locale);
}
