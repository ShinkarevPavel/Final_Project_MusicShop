package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class OrderProcessingCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        InstrumentService instrumentService = new InstrumentServiceImpl();

        try {
            Map<Instrument, Integer> items = instrumentService.getUserBucket(user.getId());
            double total = 0;
            for (Map.Entry<Instrument, Integer> item : items.entrySet()) {
                total += (item.getKey().getPrice() * item.getValue());
            }
            request.setAttribute(TOTAL_CART, total);
            request.setAttribute(CART_ITEMS, items);
        } catch (ServiceException | NumberFormatException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
        }

        router.setPagePath(ORDER_PAGE);
        return router;
    }
}
