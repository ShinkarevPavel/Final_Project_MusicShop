package com.shinkarev.finalproject.validator;

public enum UserValidator {
    EMAIL("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "email", "page.registration.errors.email_error"),
    LOGIN("^[\\w@#$%^&+=]{7,25}$", "login", "page.registration.errors.login_error"),
    NICKNAME("^[\\w@#$%^&+=]{2,30}$", "nickname", "page.registration.errors.nickname_error"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{12,45}$", "password", "page.registration.errors.password_error"),
    CHECKPASSWORD("checkPassword", "page.registration.errors.confirm_password_error"),
    NAME("[a-zA-Zа-яА-ЯЁё]{3,35}", "name" , "page.registration.errors.name_surename_error"),
    SURENAME("[a-zA-Zа-яА-ЯЁё]{3,35}", "surename", "page.registration.errors.name_surename_error");

    private String regExp;
    private String fieldName;
    private String message;

    UserValidator(String regExp, String fieldName, String message) {
        this.regExp = regExp;
        this.fieldName = fieldName;
        this.message = message;
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
