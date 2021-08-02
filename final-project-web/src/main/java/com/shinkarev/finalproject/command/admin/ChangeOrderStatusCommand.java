package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeOrderStatusCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        String status = request.getParameter("newStatus");
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            if (!orderService.changeOrderStatusByOrderId(Long.parseLong(orderId), OderType.valueOf(status))) {
//                request.setAttribute(); sent error message on jsp
            }
            router.setRouterType(Router.RouterType.REDIRECT);
            router.setPagePath(request.getHeader(ParamName.REFERER));
        } catch (ServiceException e) {
//            todo
        }
        return router;
    }
}
