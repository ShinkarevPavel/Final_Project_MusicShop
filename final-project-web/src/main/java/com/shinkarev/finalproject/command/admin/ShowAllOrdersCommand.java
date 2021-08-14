package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowAllOrdersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders ;
        try {
            orders = orderService.findAllOrders();
            if (orders.size() != 0) {
                request.setAttribute(ParamName.ORDERS_PARAM, orders);
            } else {
                request.setAttribute(ParamName.ADMIN_MESSAGE, "Found nothing");
            }
            router.setPagePath(PageName.SHOW_ALL_ORDERS);
        } catch (ServiceException ex) {
            request.setAttribute(ParamName.ERRORS_ON_ERROR_PAGE, "Oops");
            router.setPagePath(PageName.ERROR_PAGE);
        }
        return router;
    }
}
