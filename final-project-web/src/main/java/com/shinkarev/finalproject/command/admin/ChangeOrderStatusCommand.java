package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.ERRORS_ON_ERROR_PAGE;

public class ChangeOrderStatusCommand implements Command {
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        String orderId = request.getParameter(ParamName.ORDER_PARAM);
        String status = request.getParameter(ParamName.ENTITY_NEW_TYPE_PARAM);
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            if (!orderService.changeOrderStatusByOrderId(Long.parseLong(orderId), OderType.valueOf(status))) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong");
                router.setPagePath(ERROR_PAGE);
            }

        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
            router.setPagePath(ERROR_PAGE);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
