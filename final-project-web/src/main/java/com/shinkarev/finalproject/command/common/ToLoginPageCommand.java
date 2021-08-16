package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import com.shinkarev.musicshop.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.shinkarev.finalproject.command.PageName.*;
import static com.shinkarev.finalproject.command.ParamName.*;


public class ToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        if (user != null) {
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
                    String locale = (String) request.getSession().getAttribute(LOCALE);
                    request.setAttribute(LOGIN_ERROR, LocaleSetter.getInstance().getMassage(PAGE_ERRORS_REGISTRATION_CONFIRMING, locale));
                    router.setPagePath(PageName.LOGIN_PAGE);
                }
                default -> router.setPagePath(LOGIN_PAGE);
            }
        } else {
            router.setPagePath(LOGIN_PAGE);
        }

        return router;
    }
}
