package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
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

import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Order processing command.
 * Used by clients for control order details.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class OrderProcessingCommand implements Command {
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

        try {
            Map<Instrument, Integer> items = instrumentService.getUserBucket(user.getId());
            double total = 0;
            for (Map.Entry<Instrument, Integer> item : items.entrySet()) {
                total += (item.getKey().getPrice() * item.getValue());
            }
            request.setAttribute(TOTAL_CART, total);
            request.setAttribute(CART_ITEMS, items);
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error with order processing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setPagePath(ORDER_PAGE);
        return router;
    }
}
