package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.UserValidator;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class LoginCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private Router router = new Router();

    @Override
    public Router execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);
        if (user != null) {
            request.setAttribute(USER, user);
            System.out.println(user.getRole());
            switch (user.getRole()) {
                case ADMIN -> router.setPagePath(ADMIN_PAGE);
                case CLIENT -> router.setPagePath(CLIENT_PAGE);
                default -> router.setPagePath(LOGIN_PAGE);
            }
        } else {
            logger.log(Level.DEBUG, "Session is empty");
            getUser(request, router);
        }
        return router;
    }

    private void getUser(HttpServletRequest request, Router router) {
        if (request.getMethod().equals(METHOD_POST)) {
            String locale = (String) request.getSession().getAttribute(LOCALE);
            User user;
            String login = request.getParameter(UserValidator.LOGIN.getFieldName());
            String password = request.getParameter(UserValidator.PASSWORD.getFieldName());
            UserServiceImpl userService = new UserServiceImpl();
            Optional<User> optionalUser = userService.login(login, password);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                logger.log(Level.DEBUG, "Logged user is present on DB" + user);
                request.getSession().setAttribute(USER, user);
                request.setAttribute(USER, user);
                if (user.getStatus() != UserStatusType.BLOCKED) {
                    if (user.getStatus().equals(UserStatusType.ACTIVE)) {
                        switch (user.getRole()) {
                            case ADMIN -> router.setPagePath(ADMIN_PAGE);
                            case CLIENT -> router.setPagePath(PageName.CLIENT_PAGE);
                            default -> router.setPagePath(LOGIN_PAGE);
                        }
                    }
                } else {
                    request.setAttribute(USER, user);
                    router.setPagePath(PageName.LIMIT_ACCESS_PAGE);
                }
            } else {
                request.setAttribute(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(PAGE_ERRORS_LOGIN_PASSWORD, locale));
                router.setPagePath(PageName.LOGIN_PAGE);
            }
        }
    }
}
