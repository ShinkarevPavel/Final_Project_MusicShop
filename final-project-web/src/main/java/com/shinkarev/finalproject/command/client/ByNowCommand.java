package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.InstrumentService;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.shinkarev.finalproject.command.ParamName.*;

public class ByNowCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String instrumentId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentService instrumentService = new InstrumentServiceImpl();
        try {
            if (user != null) {
                if (!instrumentService.isInBucket(user.getId(), Long.parseLong(instrumentId))) {
                    if (instrumentService.addItemToBucket(user.getId(), Long.parseLong(instrumentId))) {
                        router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
                        CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
                    } else {
                        request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops");
                        router.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    router.setPagePath(PageName.CLIENT_BUCKET_PAGE);
                    CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
                }
            }
        } catch (ServiceException | NumberFormatException ex) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Error with loading users form db. Reason: " + ex.getMessage());
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
