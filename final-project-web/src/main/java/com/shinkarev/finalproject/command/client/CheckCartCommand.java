package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;


import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class CheckCartCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        Map<Instrument, Integer> itemsFromBucket;
        try {
            itemsFromBucket = instrumentService.getUserBucket(user.getId());
            request.setAttribute("items", itemsFromBucket);
            CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }
        router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
        return router;
    }
}
