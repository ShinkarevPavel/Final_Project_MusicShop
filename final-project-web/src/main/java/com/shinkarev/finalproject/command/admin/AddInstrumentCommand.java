package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.ImageConverter;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

import static com.shinkarev.finalproject.command.Router.RouterType.*;
import static com.shinkarev.finalproject.validator.InstrumentValidator.*;

/**
 * Add {@link Instrument} command. Used for adding {@link Instrument} to data base
 * by Admin.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class AddInstrumentCommand implements Command {
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
            String instrumentRating = request.getParameter(INSTRUMENT_RATING.getFieldName());
            String instrumentType = request.getParameter(INSTRUMENT_TYPE_PARAM);
            String instrumentStatus = request.getParameter(INSTRUMENT_STATUS_PARAM);

            String locale = (String) request.getSession().getAttribute(LOCALE);

            Map<String, String> registrationValues = new HashMap<>();
            registrationValues.put(INSTRUMENT_NAME.getFieldName(), instrumentName);
            registrationValues.put(INSTRUMENT_BRAND.getFieldName(), instrumentBrand);
            registrationValues.put(INSTRUMENT_COUNTRY.getFieldName(), instrumentCountry);
            registrationValues.put(INSTRUMENT_PRICE.getFieldName(), instrumentPrice);
            registrationValues.put(INSTRUMENT_DESCRIPTION.getFieldName(), instrumentDescription);
            registrationValues.put(INSTRUMENT_RATING.getFieldName(), instrumentRating);
            registrationValues.put(INSTRUMENT_TYPE_PARAM, instrumentType);
            registrationValues.put(INSTRUMENT_STATUS_PARAM, instrumentStatus);

            try {
                InputDataValidator creationValidator = ValidatorProvider.INSTRUMENT_CREATION_VALIDATOR;
                Map<String, String> errors = creationValidator.checkValues(registrationValues, locale);
                if (errors.isEmpty()) {
                    List<InputStream> images;

                    images = ImageConverter.convertImage(request);
                    Instrument instrument = new Instrument(
                            instrumentName,
                            instrumentBrand,
                            instrumentCountry,
                            Double.parseDouble(instrumentPrice),
                            Integer.parseInt(instrumentRating),
                            instrumentDescription,
                            InstrumentStatusType.valueOf(instrumentStatus),
                            InstrumentType.valueOf(instrumentType));

                    InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
                    if (instrumentService.addInstrument(instrument, images)) {
                        router.setRouterType(REDIRECT);
                        router.setPagePath(request.getContextPath() + ADMIN_PAGE);
                        logger.log(Level.DEBUG, "Instrument was added");
                    }
                } else {
                    request.setAttribute(REGISTRATION_VALUES, registrationValues);
                    request.setAttribute(ERRORS_LIST, errors);
                    router.setPagePath(ADD_INSTRUMENT);
                }
            } catch (ServiceException | IllegalStateException | NumberFormatException ex) {
                logger.log(Level.ERROR, "Error creating instrument");
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ADD_DATA, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            } catch (IOException | ServletException ex) {
                logger.log(Level.ERROR, "Error creating instrument", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ADD_IMAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            router.setPagePath(ADD_INSTRUMENT);
        }
        return router;
    }
}
