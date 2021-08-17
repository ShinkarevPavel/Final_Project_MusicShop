package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Find {@link Order} by type command.
 * Used by clients for displaying {@link Order} with  certain status.
 * This command use both -for users with admin role
 * and for users with client role
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class FindOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        OrderService orderService = ServiceProvider.ORDER_SERVICE;
        UserService userService = ServiceProvider.USER_SERVICE;
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
                request.setAttribute(ParamName.ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
        } catch (ServiceException | NumberFormatException | IllegalStateException ex) {
            logger.log(Level.ERROR, "Error of order finding", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
