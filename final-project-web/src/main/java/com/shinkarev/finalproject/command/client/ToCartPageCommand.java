package com.shinkarev.finalproject.command.client;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.CartController;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;

import static com.shinkarev.finalproject.command.PageName.CLIENT_BUCKET_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.ParamName.PAGE_ERROR_ERROR_PAGE;

public class ToCartPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        User user = (User) request.getSession().getAttribute(USER);
        try {
            CartController.cartQuantityControl(request, user.getId());
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error of getting user's cart items", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        router.setPagePath(CLIENT_BUCKET_PAGE);
        return router;
    }
}
