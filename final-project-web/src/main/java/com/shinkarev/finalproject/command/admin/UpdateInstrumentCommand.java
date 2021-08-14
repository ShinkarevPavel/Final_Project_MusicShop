package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.validator.InstrumentValidator;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class UpdateInstrumentCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        InstrumentService instrumentService = new InstrumentServiceImpl();
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);

        try {
            Instrument instrument;
            Optional<Instrument> optionalInstrument = instrumentService.findInstrumentById(Long.parseLong(instrumentId));
            if (optionalInstrument.isPresent()) {
               instrument = optionalInstrument.get();
               Map<String, String> updateValues = new HashMap<>();
               updateValues.put(InstrumentValidator.INSTRUMENT_NAME.getFieldName(), instrument.getName());
               updateValues.put(InstrumentValidator.INSTRUMENT_BRAND.getFieldName(), instrument.getBrand());
               updateValues.put(InstrumentValidator.INSTRUMENT_COUNTRY.getFieldName(), instrument.getCountry());
               updateValues.put(InstrumentValidator.INSTRUMENT_PRICE.getFieldName(), String.valueOf(instrument.getPrice()));
               updateValues.put(InstrumentValidator.INSTRUMENT_RATING.getFieldName(), String.valueOf(instrument.getRating()));
               updateValues.put(InstrumentValidator.INSTRUMENT_DESCRIPTION.getFieldName(), instrument.getDescription());
               updateValues.put(INSTRUMENT_ID_PARAM, instrumentId);
               request.setAttribute(UPDATE_INSTRUMENT_PARAM, updateValues);
               router.setPagePath(UPDATE_INSTRUMENT_PAGE);
            }
        } catch (ServiceException | NumberFormatException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
