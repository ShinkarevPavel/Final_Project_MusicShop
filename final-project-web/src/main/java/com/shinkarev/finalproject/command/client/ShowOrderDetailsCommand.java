package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
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

import java.util.Optional;

import static com.shinkarev.finalproject.command.ParamName.*;

public class ShowOrderDetailsCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String userId = request.getParameter(USER_ID_PARAM);
        OrderService orderService = ServiceProvider.ORDER_SERVICE;
        UserService userService = ServiceProvider.USER_SERVICE;
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
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error with order details showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
