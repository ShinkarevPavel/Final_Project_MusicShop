package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InstrumentValidator;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Update {@link Instrument}s command.
 * Used by admin for updating instrument.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class UpdateInstrumentCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
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
        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.ERROR, "Error of updating instrument", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
