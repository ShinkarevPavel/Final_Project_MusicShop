package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class SetInstrumentRatingCommand implements Command {
    private static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String mark = request.getParameter(ParamName.RATING);
        User user = (User) request.getSession().getAttribute(USER);
        Instrument instrument = (Instrument) request.getSession().getAttribute(INSTRUMENT_PARAM);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        try {
            if (user != null && instrument != null) {
                if (instrumentService.isInBucket(user.getId(), instrument.getInstrument_id())) {
                    request.setAttribute(Long.toString(instrument.getInstrument_id()), instrument.getInstrument_id());
                }
                if (instrumentService.isRated(user.getId(), instrument.getInstrument_id())) {
                    request.setAttribute(RATING_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_RATING , locale));
                } else {
                    instrumentService.addInstrumentRating(user.getId(), instrument.getInstrument_id(), Integer.parseInt(mark));
                    request.setAttribute(RATING_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_RATING_DONE , locale));
                }
            } else {
                request.setAttribute(RATING_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_RATING_ERROR , locale));
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error with setting items rating", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
        return router;
    }
}
