package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.UserValidator;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserStatusType;
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
 * {@link User} Login command. Check there is {@link User} in system
 * and if not, check input by user data in data base.
 *
 * @see Command
 * @see com.shinkarev.finalproject.command.Command
 */
public class LoginCommand implements Command {
    private final Logger logger = LogManager.getLogger();

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
        User user = (User) request.getSession().getAttribute(USER);
        if (user != null) {
            if (user.getStatus().equals(UserStatusType.BLOCKED)) {
                router.setPagePath(BLOCKED_PAGE);
            } else {
                request.setAttribute(USER, user);
                userPageControl(request, user, router);
            }
        } else {
            logger.log(Level.DEBUG, "Session is empty");
            getUser(request, router);
        }
        return router;
    }

    private void getUser(HttpServletRequest request, Router router) {
        String method = request.getMethod();
        if (method.equals(METHOD_POST)) {
            String locale = (String) request.getSession().getAttribute(LOCALE);
            User user;
            String login = request.getParameter(UserValidator.LOGIN.getFieldName());
            String password = request.getParameter(UserValidator.PASSWORD.getFieldName());
            UserService userService = ServiceProvider.USER_SERVICE;
            try {
                Optional<User> optionalUser = userService.login(login, password);
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    logger.log(Level.DEBUG, "Logged user is present on DB" + user);
                    if (user.getStatus().equals(UserStatusType.ACTIVE)) {
                        userPageControl(request, user, router);
                    } else {
                        request.getSession().setAttribute(USER, user);
                        router.setPagePath(BLOCKED_PAGE);
                    }
                } else {
                    request.setAttribute(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(PAGE_ERRORS_LOGIN_PASSWORD, locale));
                    router.setPagePath(LOGIN_PAGE);
                }
            } catch (ServiceException ex) {
                logger.log(Level.ERROR, "Error of user adding", ex);
                request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
                router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }


        } else {
            router.setPagePath(LOGIN_PAGE);
        }
    }

    private void userPageControl(HttpServletRequest request, User user, Router router) {
        String locale = (String) request.getSession().getAttribute(LOCALE);
        switch (user.getRole()) {
            case ADMIN -> {
                request.getSession().setAttribute(USER, user);
                request.setAttribute(USER, user);
                router.setPagePath(ADMIN_PAGE);
            }
            case CLIENT -> {
                request.getSession().setAttribute(USER, user);
                request.setAttribute(USER, user);
                router.setPagePath(MAIN_PAGE);
            }
            case GUEST -> {
                request.setAttribute(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(PAGE_ERRORS_REGISTRATION_CONFIRMING, locale));
                router.setPagePath(LOGIN_PAGE);
            }
            default -> router.setPagePath(LOGIN_PAGE);
        }
    }
}
