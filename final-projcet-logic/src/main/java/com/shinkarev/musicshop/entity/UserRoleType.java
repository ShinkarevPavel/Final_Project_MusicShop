package com.shinkarev.musicshop.entity;

public enum UserRoleType {
    ADMIN,
    CLIENT,
    GUEST;

    public static int ordinal(UserRoleType role) {
        return role.ordinal() + 1;
    }
}
