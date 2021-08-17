package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
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

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;

/**
 * Save updated {@link Instrument} command.
 * Used by admin for saving instruments after
 * updating for sending to client new information.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */


public class SaveUpdatedInstrumentCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router}
     * @throws ServiceException if the request could not be handled.
     */

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
                InputDataValidator creationValidator = ValidatorProvider.EDIT_INSTRUMENT_VALIDATOR;
                Map<String, String> errors = creationValidator.checkValues(updateValues, locale);
                if (errors.isEmpty()) {
                    Instrument instrument = new Instrument(
                            Long.parseLong(instrumentId),
                            instrumentName,
                            instrumentBrand,
                            instrumentCountry,
                            Double.parseDouble(instrumentPrice),
                            instrumentDescription);
                    InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
                    if (instrumentService.update(instrument)) {
                        router.setPagePath(ADMIN_PAGE);
                        request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN_UPDATE, locale));
                        logger.log(Level.DEBUG, "Instrument was updated");
                    }
                } else {
                    request.setAttribute(UPDATE_INSTRUMENT_PARAM, updateValues);
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(UPDATE_INSTRUMENT_PAGE);
                }
            } catch (ServiceException | IllegalStateException | NumberFormatException ex) {
                logger.log(Level.ERROR, "Instrument wasn't updated ", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_CHANGE_DATA, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setPagePath(UPDATE_INSTRUMENT_PAGE);
        }
        return router;
    }
}
