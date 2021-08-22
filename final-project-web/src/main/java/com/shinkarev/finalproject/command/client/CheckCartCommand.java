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


import java.util.Map;

import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Check cart command.
 * Used by clients for checking {@link Instrument}s in their cart.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */


public class CheckCartCommand implements Command {
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
        User user = (User) request.getSession().getAttribute(USER);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        Map<Instrument, Integer> itemsFromBucket;
        try {
            itemsFromBucket = instrumentService.getUserBucket(user.getId());
            request.setAttribute(CART_ITEMS, itemsFromBucket);
            CartController.cartQuantityControl(request, user.getId());
            router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error of cart checking", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
        return router;
    }
}
