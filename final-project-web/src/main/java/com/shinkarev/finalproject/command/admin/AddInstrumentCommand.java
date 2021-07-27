package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.InstrumentCreationValidator;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.ParamName.INSTRUMENT_STATUS;
import static com.shinkarev.finalproject.command.ParamName.INSTRUMENT_TYPE;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;

public class AddInstrumentCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        if (request.getMethod().equals(METHOD_POST)) {
            String instrumentName = request.getParameter(INSTRUMENT_NAME.getFieldName());
            String instrumentBrand = request.getParameter(INSTRUMENT_BRAND.getFieldName());
            String instrumentCountry = request.getParameter(INSTRUMENT_COUNTRY.getFieldName());
            String instrumentPrice = request.getParameter(INSTRUMENT_PRICE.getFieldName());
            String instrumentDescription = request.getParameter(INSTRUMENT_DESCRIPTION.getFieldName());
            String instrumentRating = request.getParameter(INSTRUMENT_RATING.getFieldName());
            String instrumentType = request.getParameter(INSTRUMENT_TYPE);
            String instrumentStatus = request.getParameter(INSTRUMENT_STATUS);
            String locale = (String) request.getSession().getAttribute(LOCALE);

            Map<String, String> registrationValues = new HashMap<>();
            registrationValues.put(INSTRUMENT_NAME.getFieldName(), instrumentName);
            registrationValues.put(INSTRUMENT_BRAND.getFieldName(), instrumentBrand);
            registrationValues.put(INSTRUMENT_COUNTRY.getFieldName(), instrumentCountry);
            registrationValues.put(INSTRUMENT_PRICE.getFieldName(), instrumentPrice);
            registrationValues.put(INSTRUMENT_DESCRIPTION.getFieldName(), instrumentDescription);
            registrationValues.put(INSTRUMENT_RATING.getFieldName(), instrumentRating);
            registrationValues.put(INSTRUMENT_TYPE, instrumentType);
            registrationValues.put(INSTRUMENT_STATUS, instrumentStatus);

            InstrumentCreationValidator creationValidator = new InstrumentCreationValidator();
            Map<String, String> errors = creationValidator.checkValues(registrationValues, locale);
            if (errors.isEmpty()) {
                Instrument instrument = new Instrument(instrumentName, instrumentBrand, instrumentCountry, Double.parseDouble(instrumentPrice),
                        Integer.parseInt(instrumentRating), instrumentDescription, InstrumentStatusType.valueOf(instrumentStatus), InstrumentType.valueOf(instrumentType));
                InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
                try {
                    if (instrumentService.addInstrument(instrument)) {
                        router.setPagePath(ADMIN_PAGE);
                    }
                } catch (ServiceException e) {
                    request.setAttribute(ERRORS_ON_ERROR_PAGE, "Error of adding instrument");
                    router.setPagePath(ERROR_PAGE);
                }
            } else {
                request.setAttribute(REGISTRATION_VALUES, registrationValues);
                request.setAttribute(ERRORS_LIST, errors);
                router.setPagePath(ADD_INSTRUMENT);
            }
        }
        return router;
    }
}
