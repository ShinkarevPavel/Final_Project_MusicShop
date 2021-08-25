package com.shinkarev.musicshop.entity;

public enum InstrumentType {
    GUITARS,
    DRUMS,
    KEYBOARDS,
    OTHER;

    public static int ordinal(InstrumentType type) {
        return type.ordinal() + 1;
    }
}
