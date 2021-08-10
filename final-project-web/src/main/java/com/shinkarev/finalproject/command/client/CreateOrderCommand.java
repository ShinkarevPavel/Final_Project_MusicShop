package com.shinkarev.finalproject.command.client;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.EmailService;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class CreateOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String address = request.getParameter(ADDRESS_DELIVERY);
        User user = (User) request.getSession().getAttribute(USER);
        String[] instrumentId = request.getParameterValues(INSTRUMENT_ID_PARAM);
        String[] quantity = request.getParameterValues(ITEM_QUANTITY);
        String total = request.getParameter(TOTAL_CART);
        String payment = request.getParameter(PAYMENT);

        Map<Long, Integer> orderItems = new HashMap<>();
        for (int i = 0; i < instrumentId.length; i++) {
            orderItems.put(Long.parseLong(instrumentId[i]), Integer.parseInt(quantity[i]));
        }

        Order order = new Order(user.getId(), LocalDateTime.now(), Double.parseDouble(total), address, OderType.CREATED, payment);
        OrderServiceImpl orderService = new OrderServiceImpl();
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            if (orderService.addOrder(order, orderItems)) {
                if (instrumentService.clearUserBucket(user.getId())) {
                    for (String s : instrumentId) {
                        request.getSession().removeAttribute(s);
                    }
                }
                EmailService emailService = new EmailServiceImpl();
                emailService.sendEmail(user.getEmail(), "Your order was created. You can control and track it in your acc. Thanks that chose our shop");
                request.setAttribute("message", "Your order created");
                router.setPagePath(CLIENT_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
