package com.shinkarev.finalproject.validator;

import com.shinkarev.musicshop.entity.InstrumentType;

public enum InstrumentValidator {
    INSTRUMENT_NAME("^[\\w\\s,.А-Яа-я():;!?@#$%^&\\-+=]{2,45}$","instrument_name", "page.instrument.instrument_name.message"),
    INSTRUMENT_BRAND("^[\\w\\s,.А-Яа-я():;!?@#$%^&\\-+=]{2,25}$", "instrument_brand", "page.instrument.instrument_brand.message"),
    INSTRUMENT_COUNTRY("^[\\w\\s,.А-Яа-я():;!?@#$%^&\\-+=]{2,45}$" ,"instrument_country", "page.instrument.instrument_country.message"),
    INSTRUMENT_PRICE("^(\\d+\\.\\d{2})$", "instrument_price", "page.instrument.instrument_price.message"),
    INSTRUMENT_DESCRIPTION("^[\\w\\s,\\\\.А-Яа-я():;'*!?@#$%^&\\-+=]{2,800}$", "instrument_description", "page.instrument.instrument_description.message"),
    INSTRUMENT_RATING("[0-5]", "instrument_rating", "page.instrument.instrument_rating.message"),
    INSTRUMENT_STATUS("page.instrument.instrument_status.message"),
    INSTRUMENT_TYPE("page.instrument.instrument_type.message");


    private String regExp;
    private String fieldName;
    private String message;


    InstrumentValidator(String regExp, String fieldName, String message) {
        this.regExp = regExp;
        this.fieldName = fieldName;
        this.message = message;
    }

    InstrumentValidator(String message) {
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
