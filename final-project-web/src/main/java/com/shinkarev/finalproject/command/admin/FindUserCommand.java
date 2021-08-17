package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
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

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Find {@link User} command.
 * Used by admin for searching certain {@link User}.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */


public class FindUserCommand implements Command {
    public static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router}
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String userId = request.getParameter(ADMIN_PARAM_SEARCH_USER_BY_ID);
        if (userId != null) {
            request.getSession().setAttribute(ADMIN_PARAM_SEARCH_USER_BY_ID, userId);
        } else {
            userId = (String) request.getSession().getAttribute(ADMIN_PARAM_SEARCH_USER_BY_ID);
        }
        User user;
        UserService userService = ServiceProvider.USER_SERVICE;
        try {
            Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                request.setAttribute(USER, user);
                router.setPagePath(USER_INFO_PAGE);
            } else {
                request.setAttribute(ADMIN_MESSAGE,  LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
            }
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error user finding", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
