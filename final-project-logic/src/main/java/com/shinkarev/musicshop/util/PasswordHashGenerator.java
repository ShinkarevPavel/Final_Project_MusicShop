package com.shinkarev.musicshop.util;

import java.util.Base64;

public class PasswordHashGenerator {

    public static String encodePassword(String password) {
        String codedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return codedPassword;
    }

    public static String decodePassword(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        return decodedPassword;
    }
}
