package com.shinkarev.musicshop.entity;

public enum InstrumentStatusType {
    AVAILABLE,
    NOT_AVAILABLE,
    ON_DEMAND;

    public static int ordinal(InstrumentStatusType status) {
        return status.ordinal() + 1;
    }
}
