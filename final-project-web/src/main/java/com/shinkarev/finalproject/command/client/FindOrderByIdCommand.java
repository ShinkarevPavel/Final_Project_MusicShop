package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindOrderByIdCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String orderId = request.getParameter("order_id");
        Optional<Order> optionalOrder;
        Order order;

        OrderServiceImpl orderService = new OrderServiceImpl();

        try {
            optionalOrder = orderService.getOderByOrderId(Long.parseLong(orderId));
            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
//                sent on page order
            } else {
                //                sent on page message -> there is no order with that id
            }
        } catch (ServiceException e) {
            // todo
        }
        return router;
    }
}
