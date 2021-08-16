package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.INSTRUMENT_STATUS;
import static com.shinkarev.finalproject.validator.InstrumentValidator.INSTRUMENT_TYPE;

public class InstrumentCreationValidator implements InputDataValidator {

    private static InputDataValidator instance;

    private InstrumentCreationValidator() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new InstrumentCreationValidator();
        }
        return instance;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) {
        Map<String, String> result = new HashMap<>();
        String instrumentName = values.get(INSTRUMENT_NAME.getFieldName());
        if (!instrumentName.matches(INSTRUMENT_NAME.getRegExp())) {
            result.put(INSTRUMENT_NAME_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_NAME.getMessage(), locale));
        }
        String instrumentBrand = values.get(INSTRUMENT_BRAND.getFieldName());
        if (!instrumentBrand.matches(INSTRUMENT_BRAND.getRegExp())) {
            result.put(INSTRUMENT_BRAND_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_BRAND.getMessage(), locale));
        }
        String instrumentCountry = values.get(INSTRUMENT_COUNTRY.getFieldName());
        if (!instrumentCountry.matches(INSTRUMENT_COUNTRY.getRegExp())) {
            result.put(INSTRUMENT_COUNTRY_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_COUNTRY.getMessage(), locale));
        }
        String instrumentPrice = values.get(INSTRUMENT_PRICE.getFieldName());
        if (!instrumentPrice.matches(INSTRUMENT_PRICE.getRegExp())) {
            result.put(INSTRUMENT_PRICE_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_PRICE.getMessage(), locale));
        }
        String instrumentDescription = values.get(INSTRUMENT_DESCRIPTION.getFieldName());
        if (!instrumentDescription.matches(INSTRUMENT_DESCRIPTION.getRegExp())) {
            result.put(INSTRUMENT_DESCRIPTION_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_DESCRIPTION.getMessage(), locale));
        }
        String instrumentRating = values.get(INSTRUMENT_RATING.getFieldName());
        if (!instrumentRating.matches(INSTRUMENT_RATING.getRegExp())) {
            result.put(INSTRUMENT_RATING_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_RATING.getMessage(), locale));
        }
        String instrumentStatus = values.get(INSTRUMENT_STATUS_PARAM);
        if (instrumentStatus == null) {
            result.put(INSTRUMENT_STATUS_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_STATUS.getMessage(), locale));
        }
        String instrumentType = values.get(INSTRUMENT_STATUS_PARAM);
        if (instrumentType == null) {
            result.put(INSTRUMENT_TYPE_ERROR, LocaleSetter.getInstance().getMassage(INSTRUMENT_TYPE.getMessage(), locale));
        }
        return result;
    }
}
