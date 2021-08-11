package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ShowOrderDetailsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String orderId = request.getParameter("orderId");
        OrderService orderService = new OrderServiceImpl();

        try {
            Optional<Order> optionalOrder = orderService.findOrderById(Long.parseLong(orderId));
            if (optionalOrder.isPresent()) {
                request.setAttribute("details", "details");
                Order order = optionalOrder.get();
                request.setAttribute("order", order);
                request.setAttribute("items", order.getItems());

            }
            router.setPagePath(PageName.CABINET_PAGE);
        } catch (ServiceException e) {
//            e.printStackTrace();
        }
        return router;
    }
}
