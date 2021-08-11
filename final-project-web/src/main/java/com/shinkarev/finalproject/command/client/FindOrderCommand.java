package com.shinkarev.finalproject.command.client;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class FindOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String orderType = request.getParameter(ENTITY_NEW_TYPE_PARAM);
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders;
        try {
            orders = orderService.findOrderByStatus(user.getId(), OderType.valueOf(orderType));
            if (orders.size() != 0) {
                request.setAttribute(ParamName.ORDERS_PARAM, orders);
            } else {
                request.setAttribute(ParamName.ORDER_MESSAGE, "You don't have " + orderType + " orders");
            }
            router.setPagePath(CABINET_PAGE);
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }

        return router;
    }
}
