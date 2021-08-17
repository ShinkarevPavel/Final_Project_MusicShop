package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.exception.ServiceException;
import com.shinkarev.musicshop.service.ServiceProvider;
import com.shinkarev.musicshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shinkarev.finalproject.command.ParamName.*;

public class ToRestorePasswordCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String key = request.getParameter(ParamName.CONFIRM_KEY);
        try {
            if (key != null) {
                logger.log(Level.DEBUG, "Email token was received " + ToRestorePasswordCommand.class);
                UserService userService = ServiceProvider.USER_SERVICE;
                Long userId = userService.getUserIdByEmailToken(key);
                logger.log(Level.DEBUG, "User = " + userId);
                request.getSession().setAttribute(USER_ID_PARAM, Long.toString(userId));
                router.setPagePath(PageName.FORGOT_PASSWORD_CHANGE);
            } else {
                logger.log(Level.DEBUG, "Email token was not received " + ToRestorePasswordCommand.class);
                router.setPagePath(PageName.MAIN_PAGE);
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error of userId getting", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
