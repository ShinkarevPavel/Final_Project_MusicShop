package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Page;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.OderType;
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

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.musicshop.dao.BaseDao.PAGE_SIZE;

public class FindOrderByType implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        request.setAttribute(MARK, MARK);
        OrderService orderService = ServiceProvider.ORDER_SERVICE;
        String type = request.getParameter(ParamName.ENTITY_NEW_TYPE_PARAM);
        List<Order> orders;
        try {
            int pageToDisplay = getPage(request);
            orders = orderService.findOrderByStatus(OderType.valueOf(type), pageToDisplay);
            int orderCount = orderService.getOrderCount(OderType.valueOf(type));
            if (!orders.isEmpty()) {
                request.setAttribute(ORDERS_PARAM, orders);
                request.setAttribute(PAGEABLE, new Page(orderCount, pageToDisplay, PAGE_SIZE));
            } else {
                request.setAttribute(ADMIN_MESSAGE,  LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
            router.setPagePath(SHOW_ALL_ORDERS);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error finding order by Type", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
