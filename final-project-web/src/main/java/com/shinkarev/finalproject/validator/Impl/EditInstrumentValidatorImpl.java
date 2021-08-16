package com.shinkarev.finalproject.validator.Impl;

import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.INSTRUMENT_DESCRIPTION;

public class EditInstrumentValidatorImpl implements InputDataValidator {

    private static InputDataValidator instance;

    private EditInstrumentValidatorImpl() {
    }

    public static InputDataValidator getInstance() {
        if (instance == null) {
            instance = new EditInstrumentValidatorImpl();
        }
        return instance;
    }

    @Override
    public Map<String, String> checkValues(Map<String, String> values, String locale) throws ServiceException {
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
        return result;
    }
}
