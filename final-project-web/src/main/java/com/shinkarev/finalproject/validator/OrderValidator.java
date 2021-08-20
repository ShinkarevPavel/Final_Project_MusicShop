package com.shinkarev.finalproject.validator;

public enum OrderValidator {
    ADDRESS("[\\wа-яА-ЯЁё,.\\/\\s+]{3,45}", "address", "page.order_create");

    OrderValidator(String regEx, String fieldName, String message) {
        this.regEx = regEx;
        this.fieldName = fieldName;
        this.message = message;
    }

    private String regEx;
    private String fieldName;
    private String message;

    public String getRegEx() {
        return regEx;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
