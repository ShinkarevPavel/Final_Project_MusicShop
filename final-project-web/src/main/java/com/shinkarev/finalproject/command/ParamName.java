package com.shinkarev.finalproject.command;

public class ParamName {

    private ParamName() {}

    /*Errors*/
    public static final String LOGIN_ERROR = "loginError";
    public static final String PASSWORD_ERROR = "passwordError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String NICKNAME_ERROR = "nicknameError";


    /*Messages*/
    public static final String MESSAGE_ERROR_LOGIN_PASSWORD = "incorrect login/password par";
    public static final String MESSAGE_ERROR_LOGIN = "this login already use";
    public static final String MESSAGE_ERROR_EMAIL = "this email already use";


    /**/
    public static final String COMMAND_PARAM = "command";
    public static final String USER = "user";
    public static final String LOCALE = "locale";
    public static final String REFERER = "referer";
    public static final String USER_LIST_PARAM = "users";
    public static final String REFERER_PARAM = "refererCommand";
    public static final String EN_LOCALE = "en-EN";
    public static final String RU_LOCALE = "ru-RU";
}
