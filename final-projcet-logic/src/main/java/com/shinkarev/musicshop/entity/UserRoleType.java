package com.shinkarev.musicshop.entity;

public enum UserRoleType {
    ADMIN,
    CLIENT,
    GUEST,
    ANONYMOUS;

    public static int ordinal(UserRoleType role) {
        return role.ordinal() + 1;
    }
}
