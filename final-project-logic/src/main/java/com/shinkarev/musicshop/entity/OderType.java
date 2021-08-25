package com.shinkarev.musicshop.entity;

public enum OderType {
    CREATED,
    PROCESSING,
    ACCEPTED,
    ON_DELIVERY,
    DELIVERED;

    public static int ordinal(OderType type) {
        return type.ordinal() + 1;
    }
}
