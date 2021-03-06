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

import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Remove from cart command.
 * Used by clients for removing {@link Instrument} from in their cart.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class RemoveFromCartCommand implements Command {
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
        String itemId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        String method = request.getMethod();
        try {
            if (method.equals(METHOD_POST)) {
                if (instrumentService.removeItemFromBucket(user.getId(), Long.parseLong(itemId))) {
                    request.getSession().removeAttribute(itemId);
                    CartController.cartQuantityControl(request, user.getId());
                } else {
                    request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
                    router.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                CartController.cartQuantityControl(request, user.getId());
            }
            router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error with removing items from cart", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
