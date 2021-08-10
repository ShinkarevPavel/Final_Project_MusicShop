package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.impl.InstrumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.CLIENT_BUCKET_PAGE;
import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;

public class RemoveFromCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        String itemId = request.getParameter(INSTRUMENT_ID_PARAM);
        InstrumentServiceImpl instrumentService = new InstrumentServiceImpl();
        try {
            if (instrumentService.removeItemFromBucket(user.getId(), Long.parseLong(itemId))) {
                request.getSession().removeAttribute(itemId);
                router = CartController.cartQuantityControl(request, instrumentService.getUserBucket(user.getId()));
            } else {

                request.setAttribute(ERRORS_ON_ERROR_PAGE, "There is no that item in your bucket");
                router.setPagePath(ERROR_PAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "Oops, something went wrong. We fix it, later ;)");
        }

        return router;
    }
}
