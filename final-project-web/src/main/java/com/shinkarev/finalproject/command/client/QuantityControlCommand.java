package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.finalproject.util.LocaleSetter;
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

public class QuantityControlCommand implements Command {
    private static Logger logger = LogManager.getLogger();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        User user = (User) request.getSession().getAttribute(USER);
        String quantity = request.getParameter(ITEM_QUANTITY);
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
        try {
            if (instrumentService.setInstrumentQuantity(user.getId(), Long.parseLong(instrumentId), Integer.parseInt(quantity))) {
                router = CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error with quantity controlling", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
