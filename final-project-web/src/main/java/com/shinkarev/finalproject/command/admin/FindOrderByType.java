package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class FindOrderByType implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = new OrderServiceImpl();
        String type = request.getParameter(ParamName.ENTITY_NEW_TYPE_PARAM);

        List<Order> orders;

        try {
            orders = orderService.findOrderByStatus(OderType.valueOf(type));
            if (!orders.isEmpty()) {
                request.setAttribute(ORDERS_PARAM, orders);
            } else {
                request.setAttribute(ADMIN_MESSAGE, "There is no orders");
            }
            router.setPagePath(SHOW_ALL_ORDERS);
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
