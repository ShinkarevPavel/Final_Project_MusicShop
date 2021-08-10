package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;


import static com.shinkarev.finalproject.command.ParamName.*;

public class QuantityControlCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String quantity = request.getParameter("quantity");
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            if (instrumentService.setInstrumentQuantity(user.getId(), Long.parseLong(instrumentId), Integer.parseInt(quantity))) {
                router = CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
            }
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
        }

        return router;
    }
}
