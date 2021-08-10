package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class AddToBucketCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        Instrument instrument = (Instrument) request.getSession().getAttribute(INSTRUMENT_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            if (!instrumentService.isInBucket(user.getId(), instrument.getInstrument_id())) {
                if (instrumentService.addItemToBucket(user.getId(), instrument.getInstrument_id())) {
                    request.setAttribute(Long.toString(instrument.getInstrument_id()), instrument.getInstrument_id());
                    request.setAttribute(INSTRUMENT_PARAM, instrument);
                    router.setPagePath(CLIENT_SHOW_INSTRUMENT_DETAILS);
                } else {
                    request.setAttribute(ERRORS_ON_ERROR_PAGE, "Item wasn't added to bucket");
                    router.setPagePath(ERROR_PAGE);
                }
            } else {
                router = CartController.cartQuantityControl(request,instrumentService.getUserBucket(user.getId()));
            }
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
