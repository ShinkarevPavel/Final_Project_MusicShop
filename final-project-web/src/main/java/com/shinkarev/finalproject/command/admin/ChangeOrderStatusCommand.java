package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.ParamName.*;

public class ChangeOrderStatusCommand implements Command {
    public static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String orderId = request.getParameter(ParamName.ORDER_PARAM);
        String status = request.getParameter(ParamName.ENTITY_NEW_TYPE_PARAM);
        OrderService orderService = ServiceProvider.ORDER_SERVICE;
        try {
            if (!orderService.changeOrderStatusByOrderId(Long.parseLong(orderId), OderType.valueOf(status))) {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_CHANGE_DATA, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IllegalStateException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error order status changing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
