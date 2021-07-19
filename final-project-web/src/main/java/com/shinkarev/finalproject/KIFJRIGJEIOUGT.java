package com.shinkarev.finalproject;

import java.util.Locale;
import java.util.ResourceBundle;

public class KIFJRIGJEIOUGT {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("localization", new Locale("en", "US"));
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("localization", new Locale("ru", "RU"));
        System.out.println(resourceBundle.getString("page.errors.login_password_error"));
        System.out.println(resourceBundle1.getString("page.errors.login_password_error"));
    }
}
