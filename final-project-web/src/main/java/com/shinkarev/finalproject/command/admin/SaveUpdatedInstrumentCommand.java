package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.Impl.EditInstrumentValidatorImpl;
import com.shinkarev.finalproject.validator.Impl.InstrumentCreationValidator;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;


public class SaveUpdatedInstrumentCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            String instrumentName = request.getParameter(INSTRUMENT_NAME.getFieldName());
            String instrumentBrand = request.getParameter(INSTRUMENT_BRAND.getFieldName());
            String instrumentCountry = request.getParameter(INSTRUMENT_COUNTRY.getFieldName());
            String instrumentPrice = request.getParameter(INSTRUMENT_PRICE.getFieldName());
            String instrumentDescription = request.getParameter(INSTRUMENT_DESCRIPTION.getFieldName());
            String locale = (String) request.getSession().getAttribute(LOCALE);
            String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
            Map<String, String> updateValues = new HashMap<>();
            updateValues.put(INSTRUMENT_NAME.getFieldName(), instrumentName);
            updateValues.put(INSTRUMENT_BRAND.getFieldName(), instrumentBrand);
            updateValues.put(INSTRUMENT_COUNTRY.getFieldName(), instrumentCountry);
            updateValues.put(INSTRUMENT_PRICE.getFieldName(), instrumentPrice);
            updateValues.put(INSTRUMENT_DESCRIPTION.getFieldName(), instrumentDescription);

            try {
                InputDataValidator creationValidator = new EditInstrumentValidatorImpl();
                Map<String, String> errors = creationValidator.checkValues(updateValues, locale);
                if (errors.isEmpty()) {
                    Instrument instrument = new Instrument(
                            Long.parseLong(instrumentId),
                            instrumentName,
                            instrumentBrand,
                            instrumentCountry,
                            Double.parseDouble(instrumentPrice),
                            instrumentDescription);
                    InstrumentService instrumentService = new InstrumentServiceImpl();
                    if (instrumentService.update(instrument)) {
                        router.setPagePath(ADMIN_PAGE);
                        request.setAttribute(ADMIN_MESSAGE, "Item was updated");
                    } else {
                        router.setPagePath(ADMIN_PAGE);
                        request.setAttribute(ADMIN_MESSAGE, "Error item updating");
                    }
                } else {
                    request.setAttribute(UPDATE_INSTRUMENT_PARAM, updateValues);
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(UPDATE_INSTRUMENT_PAGE);
                }
            } catch (ServiceException | NumberFormatException e) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
                router.setPagePath(ERROR_PAGE);
            }
        } else {
            router.setPagePath(UPDATE_INSTRUMENT_PAGE);
        }
        return router;
    }
}
