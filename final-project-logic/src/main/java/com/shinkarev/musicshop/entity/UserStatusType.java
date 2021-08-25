package com.shinkarev.musicshop.entity;

public enum UserStatusType {
    ACTIVE,
    BLOCKED;

    public static int ordinal(UserStatusType status) {
        return status.ordinal() + 1;
    }

    
}
