package com.shinkarev.finalproject.command.common;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.util.LocaleSetter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.shinkarev.finalproject.command.PageName.ERROR_PAGE;
import static com.shinkarev.finalproject.command.ParamName.*;
import static com.shinkarev.finalproject.command.Router.RouterType.REDIRECT;

public class WrongCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(LOCALE);
        request.setAttribute(ERRORS_ON_ERROR_PAGE, LocaleSetter.getInstance().getMassage(PAGE_ERROR_ERROR_PAGE, locale));
        router.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return router;
    }
}
