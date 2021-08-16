package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.*;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

public class ShowAllOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        OrderService orderService = ServiceProvider.ORDER_SERVICE;
        List<Order> orders;

        try {
            int pageToDisplay = getPage(request);
            orders = orderService.readByPage(pageToDisplay);
            int instrumentCount = orderService.getOrderCount();
            if (orders.size() != 0) {
                request.setAttribute(ParamName.ORDERS_PARAM, orders);
                request.setAttribute(PAGEABLE, new Page(instrumentCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
            router.setPagePath(PageName.SHOW_ALL_ORDERS);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error of all orders showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_CHANGE_DATA + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
