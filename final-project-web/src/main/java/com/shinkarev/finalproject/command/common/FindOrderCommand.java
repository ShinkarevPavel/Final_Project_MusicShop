package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.UserService;
import com.shinkarev.musicshop.service.impl.OrderServiceImpl;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * This command use both
 * for user with admin role
 * and for users with client role
 */

public class FindOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        List<Order> orders;
        User user;
        String userId = request.getParameter(USER_ID_PARAM);
        String orderType = request.getParameter(ENTITY_NEW_TYPE_PARAM);
        try {
            if (userId != null) {
                Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
                orders = orderService.findUserOrderByStatus(Long.parseLong(userId), OderType.valueOf(orderType));
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    request.setAttribute(USER, user);
                    router.setPagePath(USER_INFO_PAGE);
                }
            } else {
                user = (User) request.getSession().getAttribute(USER);
                orders = orderService.findUserOrderByStatus(user.getId(), OderType.valueOf(orderType));
                router.setPagePath(CABINET_PAGE);
            }
            if (orders.size() != 0) {
                request.setAttribute(ParamName.ORDERS_PARAM, orders);
            } else {
                request.setAttribute(ParamName.ADMIN_MESSAGE, "Didn't found orders with " + orderType + " type");
            }
        } catch (ServiceException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}
