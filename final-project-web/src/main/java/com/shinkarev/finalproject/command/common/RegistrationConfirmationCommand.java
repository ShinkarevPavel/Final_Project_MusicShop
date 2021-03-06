package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
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
 * Registration confirmation command.
 * Used for sending confirmation email to {@link User}
 * if all input data were correct
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */


public class RegistrationConfirmationCommand implements Command {
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
        String confirmationKey = request.getParameter(CONFIRM_KEY);
        UserService userService = ServiceProvider.USER_SERVICE;
        try {
            Optional<User> userOptional = userService.getUserByRegistrationKey(confirmationKey);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.userRoleController(user.getId(), UserRoleType.CLIENT);
                router.setPagePath(LOGIN_PAGE);
            } else {
                router.setPagePath(REGISTRATION_PAGE);
            }
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, "Error of registration confirmation", ex);
            request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
            router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return router;
    }
}
