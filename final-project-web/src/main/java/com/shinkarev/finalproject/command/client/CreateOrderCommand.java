package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class CreateOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String address = request.getParameter(ADDRESS_DELIVERY);
        User user = (User) request.getSession().getAttribute(USER);
        String[] instrumentId = request.getParameterValues(INSTRUMENT_ID_PARAM);
        String[] quantity = request.getParameterValues(ITEM_QUANTITY);
        String total = request.getParameter(TOTAL_CART);
        String payment = request.getParameter(PAYMENT);

        try {
            Map<Long, Integer> orderItems = new HashMap<>();
            for (int i = 0; i < instrumentId.length; i++) {
                orderItems.put(Long.parseLong(instrumentId[i]), Integer.parseInt(quantity[i]));
            }

            Order order = new Order(user.getId(), LocalDateTime.now(), Double.parseDouble(total), address, OderType.CREATED, payment);
            OrderService orderService = ServiceProvider.ORDER_SERVICE;
            InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
            if (orderService.addOrder(order, orderItems)) {
                if (instrumentService.clearUserBucket(user.getId())) {
                    for (String s : instrumentId) {
                        request.getSession().removeAttribute(s);
                    }
                }
                EmailServiceImpl.getInstance().sendEmail(user.getEmail(), EMAIL_MESSAGE);
                request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ORDER_CREATED , locale));
                router.setPagePath(CABINET_PAGE);
            }
        } catch (ServiceException | IllegalStateException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error of creating instrument", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
