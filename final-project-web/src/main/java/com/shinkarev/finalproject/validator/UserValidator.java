package com.shinkarev.finalproject.validator;

public enum UserValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "email", "email is incorrect"),
    LOGIN("^[\\w@#$%^&+=]{7,25}$", "login", "login is incorrect"),
    NICKNAME("^[\\w@#$%^&+=]{2,30}$", "nickname", "nickname is incorrect"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$", "password", "password is incorrect"),
    CHECKPASSWORD("checkPassword", "passwords are differ"),
    NAME("name"),
    SURENAME("surename");

    private String regExp;
    private String fieldName;
    private String message;

    UserValidator(String regExp, String fieldName, String message) {
        this.regExp = regExp;
        this.fieldName = fieldName;
        this.message = message;
    }

    UserValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    UserValidator(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getRegExp() {
        return regExp;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
