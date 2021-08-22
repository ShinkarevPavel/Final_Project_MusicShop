package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
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

/**
 * Add to Cart command.
 * Used by clients for adding items in their cart.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class AddToCartCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     * @throws ServiceException if the request could not be handled.
     */


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        User user = (User) request.getSession().getAttribute(USER);
        Instrument instrument = (Instrument) request.getSession().getAttribute(INSTRUMENT_PARAM);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        try {
            if (instrumentService.isInBucket(user.getId(), instrument.getInstrument_id())) {
                String method = request.getMethod();
                if (method.equals(METHOD_POST)) {
                    router.setRouterType(Router.RouterType.REDIRECT);
                    router.setPagePath(REDIRECT_TO_CART_PAGE);
                } else {
                    request.setAttribute(Long.toString(instrument.getInstrument_id()), instrument.getInstrument_id());
                    router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
                }
            } else {
                if (instrumentService.addItemToBucket(user.getId(), instrument.getInstrument_id())) {
                    request.getSession().setAttribute(Long.toString(instrument.getInstrument_id()), instrument.getInstrument_id());
                    request.setAttribute(INSTRUMENT_PARAM, instrument);
                    router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
                }
            }
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error of adding item to cart", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
