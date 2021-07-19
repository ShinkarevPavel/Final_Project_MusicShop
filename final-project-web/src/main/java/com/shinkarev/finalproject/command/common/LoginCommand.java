package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.finalproject.validator.UserValidator;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.util.Enumeration;
import java.util.Locale;
import java.util.Optional;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        if (user != null) {
            request.setAttribute(USER, user);
            switch (user.getRole()) {
                case ADMIN -> router.setPagePath(ADMIN_PAGE);
                case CLIENT -> router.setPagePath(CLIENT_PAGE);
                default -> router.setPagePath(LOGIN_PAGE);
            }
        } else {
            getUser(request, router);
        }
        return router;
    }

    private void getUser(HttpServletRequest request, Router router) {
        User user;
        String login = request.getParameter(UserValidator.LOGIN.getFieldName());
        String password = request.getParameter(UserValidator.PASSWORD.getFieldName());
            UserServiceImpl userService = new UserServiceImpl();
        Optional<User> optionalUser = userService.login(login, password);




        if (optionalUser.isPresent()) {
            user = optionalUser.get();
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
            request.setAttribute(LOGIN_ERROR, LocaleSetter.getInstance().getMassage("page.errors.login_password_error", request));
            router.setPagePath(PageName.LOGIN_PAGE);
        }
    }
}
