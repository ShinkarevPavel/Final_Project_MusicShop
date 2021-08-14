package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shinkarev.finalproject.command.ParamName.*;

public class GetOderByStatusCommand implements Command {
    private Router router = new Router();
    @Override
    public Router execute(HttpServletRequest request) {
        String userId = request.getParameter(USER_ID_PARAM);
        String status = request.getParameter("new Status");
        List<Order> orders;
        OrderServiceImpl orderService = new OrderServiceImpl();

        try {
            orders = orderService.findUserOrderByStatus(Long.parseLong(userId), OderType.valueOf(status));
            if (orders.size() != 0) {
//                request.setAttribute(); sent list on jsp
            } else {
//                request.setAttribute(); error message on jsp
            }
            router.setRouterType(Router.RouterType.REDIRECT);
            router.setPagePath(request.getHeader(ParamName.REFERER));
        } catch (ServiceException e) {
//            todo
        }
        return router;
    }
}
