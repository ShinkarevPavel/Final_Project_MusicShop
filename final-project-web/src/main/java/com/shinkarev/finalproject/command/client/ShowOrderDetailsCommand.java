package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowOrderDetailsCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userId = request.getParameter(USER_ID_PARAM);
        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        String orderId = request.getParameter(ORDER_PARAM);


        try {
            Optional<Order> optionalOrder = orderService.findOrderById(Long.parseLong(orderId));
            if (optionalOrder.isPresent()) {
                request.setAttribute(ORDER_DETAILS, ORDER_DETAILS);
                Order order = optionalOrder.get();
                request.setAttribute(ParamName.ORDER, order);
                request.setAttribute(ParamName.COLLECTIONS_PARAM, order.getItems());
            }
            if (userId != null) {
                User user;
                Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    request.setAttribute(USER, user);
                }
                router.setPagePath(PageName.USER_INFO_PAGE);
            } else {
                router.setPagePath(PageName.CABINET_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}