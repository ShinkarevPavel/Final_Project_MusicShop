package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.shinkarev.finalproject.command.ParamName.EN_LOCALE;
import static com.shinkarev.finalproject.command.ParamName.RU_LOCALE;

public class ChangeLocalCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setRouterType(Router.RouterType.REDIRECT);

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(ParamName.LOCALE);
        if (locale == null || RU_LOCALE.equals(locale)) {
            locale = EN_LOCALE;

        } else {
            locale = RU_LOCALE;
        }
        session.setAttribute(ParamName.LOCALE, locale);
        router.setPagePath(request.getHeader(ParamName.REFERER));
        return router;
    }
}
