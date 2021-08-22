package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.util.OrderController;
import com.shinkarev.finalproject.validator.InputDataValidator;
import com.shinkarev.finalproject.validator.ValidatorProvider;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.OderType;
import com.shinkarev.musicshop.entity.Order;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.OrderService;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.impl.EmailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.validator.OrderValidator.ADDRESS;

/**
 * Create order command
 * Used by clients for buying chosen {@link Instrument}s.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class CreateOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String address = request.getParameter(ADDRESS_DELIVERY);
        User user = (User) request.getSession().getAttribute(USER);
        String[] instrumentId = request.getParameterValues(INSTRUMENT_ID_PARAM);
        String[] quantity = request.getParameterValues(ITEM_QUANTITY);
        String total = request.getParameter(TOTAL_CART);
        String payment = request.getParameter(PAYMENT);
        String method = request.getMethod();
        try {
            if (method.equals(METHOD_POST)) {

                InputDataValidator orderValidator = ValidatorProvider.ORDER_VALIDATOR;
                Map<String, String> registrationValues = new HashMap<>();
                registrationValues.put(ADDRESS.getFieldName(), address);

                Map<String, String> errors = orderValidator.checkValues(registrationValues, locale);
                if (errors.isEmpty()) {
                    OrderService orderService = ServiceProvider.ORDER_SERVICE;
                    InstrumentService instrumentService = ServiceProvider.INSTRUMENT_SERVICE;
                    Order order = new Order(user.getId(), LocalDateTime.now(), Double.parseDouble(total), address, OderType.CREATED, payment);
                    Map<Long, Integer> orderItems = OrderController.orderItemCreat(instrumentId, quantity);
                    if (orderService.addOrder(order, orderItems)) {
                        if (instrumentService.clearUserBucket(user.getId())) {
                            OrderController.removeCartFromSession(request, instrumentId);
                        }
                        EmailServiceImpl.getInstance().sendEmail(user.getEmail(), EMAIL_MESSAGE);
                        request.setAttribute(ADMIN_MESSAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ORDER_CREATED, locale));
                        router.setRouterType(Router.RouterType.REDIRECT);
                        router.setPagePath(REDIRECT_CABINET_PAGE);
                    }
                } else {
                    request.setAttribute(ADDRESS_ERROR, LocaleSetter.getInstance().getMassage(ADDRESS.getMessage(), locale));
                    request.setAttribute(ERRORS_LIST, errors);
                    CartController.cartQuantityControl(request, user.getId());
                    router.setPagePath(ORDER_PAGE);
                }
            } else {
                CartController.cartQuantityControl(request, user.getId());
                router.setPagePath(ORDER_PAGE);
            }
        } catch (ServiceException | IllegalStateException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error of creating instrument", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
