package com.shinkarev.finalproject.command.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.Instrument;
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
 * {@link User} info command.
 * Used by admin for getting information about certain {@link User}.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */

public class UserInfoCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request the HttpServletRequest
     * @return the {@link Router} that contains information about next page
     * and data that will be display on client's page.
     *
     * @throws ServiceException if the request could not be handled.
     */

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String userId = request.getParameter(USER_ID_PARAM);
        UserService userService = ServiceProvider.USER_SERVICE;
        User user;
        try {
            Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                request.setAttribute(USER, user);
                router.setPagePath(USER_INFO_PAGE);
            } else {
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_MESSAGE_ADMIN, locale));
                router.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | NumberFormatException ex) {
            logger.log(Level.ERROR, "Error of user info showing", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE + ex.getMessage(), locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
