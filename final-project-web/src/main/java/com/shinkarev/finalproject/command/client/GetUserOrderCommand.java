package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GetUserOrderCommand implements Command {
    private Router router = new Router();


    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(ParamName.USER_ID_PARAM);
        List<Order> orders;
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            orders = orderService.getOderByUserId(Long.parseLong(userId));
            if (orders.size() != 0) {
                // sent on jsp orders list
            } else {
                // sent on jsp message with text -> "there is no orders" + write dao method - show orders by type !!!!
            }
            router.setRouterType(Router.RouterType.REDIRECT);
            router.setPagePath(request.getHeader(ParamName.REFERER));
        } catch (ServiceException e) {
            // todo
        }
        return router;
    }
}
